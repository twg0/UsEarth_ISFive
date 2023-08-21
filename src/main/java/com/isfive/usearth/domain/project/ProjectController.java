package com.isfive.usearth.domain.project;

import com.isfive.usearth.domain.project.dto.ProjectRegisterDto;
import com.isfive.usearth.domain.project.service.FileImageService;
import com.isfive.usearth.domain.project.service.ProjectService;
import com.isfive.usearth.domain.project.webDto.ProjectCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
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



}
