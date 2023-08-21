package com.isfive.usearth.domain.project;

import com.isfive.usearth.domain.project.dto.ProjectRegisterDto;
import com.isfive.usearth.domain.project.service.FileImageService;
import com.isfive.usearth.domain.project.service.ProjectService;
import com.isfive.usearth.domain.project.webDto.ProjectCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        projectService.createProject(registerDto, projectImageList);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping
    public Page<ProjectResponse> findProjects(Pageable pageable) {
        return projectService.readAllProject(pageable);
    }

}
