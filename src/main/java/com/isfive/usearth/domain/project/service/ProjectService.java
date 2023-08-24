package com.isfive.usearth.domain.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.project.dto.ProjectRegisterDto;
import com.isfive.usearth.domain.project.dto.ProjectResponse;
import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.entity.ProjectFileImage;
import com.isfive.usearth.domain.project.entity.Tag;
import com.isfive.usearth.domain.project.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

//    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
//    private final MakerRepository makerRepository;
    private final TagService tagService;

    public void createProject(ProjectRegisterDto dto, List<FileImage> fileList) {
        Project project = dto.toEntity();
        projectRepository.save(project);

        // 메이커 등록
//        Maker maker = makerRepository.findByName(dto.getMaker());
//        project.setMaker(maker);

        // 태그 리스트 등록
        List<Tag> tagList = tagService.convertTagStrToEntity(dto.getTagList(), project);

        // 이미지 리스트 등록
        List<ProjectFileImage> projectImageList = createProjectFileImageList(fileList, project);

        // 추가사항 저장
        projectRepository.save(project);

    }
    public Page<ProjectResponse> readAllProject(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return createProjectResponsePage(pageable, projects);
    }

    private Page<ProjectResponse> createProjectResponsePage(Pageable pageable, Page<Project> projects) {
        List<ProjectResponse> list = projects.stream()
                .map(ProjectResponse::new)
                .toList();
        return new PageImpl<>(list, pageable, projects.getTotalElements());
    }

    public ProjectFileImage createProjectFileImage(FileImage fileImage, Project project) {
        ProjectFileImage projectFileImage = ProjectFileImage.builder()
            .fileImage(fileImage)
            .build();
        projectFileImage.setProject(project);
        return projectFileImage;
    }

    public List<ProjectFileImage> createProjectFileImageList(List<FileImage> fileImageList, Project project) {
        List<ProjectFileImage> projectFileImageList = new ArrayList<>();
        for (FileImage file : fileImageList) {
            projectFileImageList.add(createProjectFileImage(file,project));
        }
        return projectFileImageList;
    }
}
