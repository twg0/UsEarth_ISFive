package com.isfive.usearth.web.project;

import java.util.List;

import com.isfive.usearth.domain.board.dto.PostsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.project.dto.ProjectRegisterDto;
import com.isfive.usearth.domain.project.dto.ProjectResponse;
import com.isfive.usearth.domain.project.service.ProjectService;
import com.isfive.usearth.web.project.dto.ProjectCreateDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final FileImageService fileImageService;

    @PostMapping
    public ResponseEntity<?> createProject(
            @RequestPart ProjectCreateDto dto,
            @RequestPart MultipartFile repImage,
            @RequestPart List<MultipartFile> projectImageList
    ) {
        ProjectRegisterDto registerDto = dto.toService(repImage);
        List<FileImage> fileImageList = fileImageService.createFileImage(projectImageList);
        projectService.createProject(registerDto, fileImageList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<Page<ProjectResponse>> findProjects(Pageable pageable) {
        Page<ProjectResponse> projectResponses = projectService.readAllProject(pageable);
        return new ResponseEntity<>(projectResponses, HttpStatus.OK);
    }

}
