package com.isfive.usearth.web.project;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.project.dto.ProjectCreate;
import com.isfive.usearth.domain.project.dto.ProjectResponse;
import com.isfive.usearth.domain.project.dto.ProjectUpdate;
import com.isfive.usearth.domain.project.dto.RewardCreate;
import com.isfive.usearth.domain.project.repository.ProjectRepository;
import com.isfive.usearth.domain.project.service.ProjectService;
import com.isfive.usearth.web.project.dto.ProjectModify;
import com.isfive.usearth.web.project.dto.ProjectRegister;
import com.isfive.usearth.web.project.dto.RewardRegister;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectService projectService;
    private final FileImageService fileImageService;

    @PostMapping
    public ResponseEntity<Void> createProject(
            Authentication auth,
            @RequestPart ProjectRegister projectRegister,           // 프로젝트 정보
            @RequestPart MultipartFile repImage,                    // 대표 이미지
            @RequestPart List<MultipartFile> projectImageList,      // 프로젝트 첨부 이미지 리스트
            @RequestPart List<RewardRegister> rewardRegisterList    // 리워드 정보 리스트
    ) {
        FileImage fileImage = fileImageService.createFileImage(repImage);
        List<FileImage> fileImageList = fileImageService.createFileImageList(projectImageList);
        List<RewardCreate> rewardCreateList = rewardRegisterList.stream()
                .map(RewardRegister::toService).collect(Collectors.toList());

        ProjectCreate projectCreate = projectRegister.toService(fileImage);
        projectService.createProject(auth.getName(), projectCreate, rewardCreateList, fileImageList);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> findProjects(Pageable pageable) {
        Page<ProjectResponse> projectResponses = projectService.readAllProject(pageable);
        return new ResponseEntity<>(projectResponses, HttpStatus.OK);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Void> updateProject(
            Authentication auth,
            @PathVariable Long projectId,
            @RequestPart ProjectModify projectModify,
            @RequestPart MultipartFile repImage,
            @RequestPart List<MultipartFile> projectImageList
    ) {
        FileImage fileImage = fileImageService.createFileImage(repImage);
        List<FileImage> fileImageList = fileImageService.createFileImageList(projectImageList);
        ProjectUpdate projectUpdate = projectModify.toService(fileImage);
        projectService.updateProject(auth.getName(), projectId, projectUpdate, fileImageList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(
            Authentication auth,
            @PathVariable Long projectId
    ) {
        projectService.deleteProject(auth, projectId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
