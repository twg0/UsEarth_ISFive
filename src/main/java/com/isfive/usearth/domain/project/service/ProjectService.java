package com.isfive.usearth.domain.project.service;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.maker.entity.Maker;
import com.isfive.usearth.domain.maker.repository.MakerRepository;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.project.dto.ProjectCreate;
import com.isfive.usearth.domain.project.dto.ProjectResponse;
import com.isfive.usearth.domain.project.dto.ProjectUpdate;
import com.isfive.usearth.domain.project.dto.RewardCreate;
import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.entity.ProjectFileImage;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.entity.Tag;
import com.isfive.usearth.domain.project.repository.ProjectFileImageRepository;
import com.isfive.usearth.domain.project.repository.ProjectRepository;

import com.isfive.usearth.domain.project.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final MakerRepository makerRepository;
    private final TagRepository tagRepository;
    private final ProjectFileImageRepository projectFileImageRepository;
    private final RewardService rewardService;
    private final TagService tagService;

    @Transactional
    public void createProject(String username, ProjectCreate projectCreate, List<RewardCreate> rewardCreateList, List<FileImage> fileList) {
        memberRepository.findByUsername(username);

        Project project = projectCreate.toEntity();
        projectRepository.save(project);

        // 대표 이미지 등록
        project.setRepImage(projectCreate.getRepImage());

        // 메이커 등록
        Maker maker = makerRepository.findByName(projectCreate.getMakerName());
        maker.addProject(project);

        // 태그 리스트 등록
        List<Tag> tagList = tagService.convertTagStrToEntity(tagService.createTagList(projectCreate.getHashTag()));
        for (Tag tag : tagList)
            project.addTag(tagRepository.save(tag));

        // 이미지 리스트 등록
        List<ProjectFileImage> projectImageList = createProjectFileImageList(fileList);
        for (ProjectFileImage image : projectImageList)
            project.addProjectFileImage(image);

        // 리워드 등록
        List<Reward> rewardList = rewardService.createReward(rewardCreateList);
        for (Reward reward : rewardList)
            project.addReward(reward);

        // 추가사항 저장
        projectRepository.save(project);

    }

    public ProjectFileImage createProjectFileImage(FileImage fileImage) {
        ProjectFileImage projectFileImage = ProjectFileImage.builder()
                .fileImage(fileImage)
                .build();
        projectFileImageRepository.save(projectFileImage);
        return projectFileImage;
    }

    public List<ProjectFileImage> createProjectFileImageList(List<FileImage> fileImageList) {
        List<ProjectFileImage> projectFileImageList = new ArrayList<>();
        for (FileImage file : fileImageList) {
            projectFileImageList.add(createProjectFileImage(file));
        }
        projectFileImageRepository.saveAll(projectFileImageList);
        return projectFileImageList;
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

    @Transactional
    public void updateProject(String username, Long projectId, ProjectUpdate projectUpdate, List<FileImage> fileList) {
        memberRepository.findByUsername(username);

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        // 프로젝트 정보 수정
        project.update(projectUpdate.toEntity());

        // 대표 이미지 수정
        project.setRepImage(projectUpdate.getRepImage());

        // 이미지 리스트 수정
        List<ProjectFileImage> oldImageList = projectFileImageRepository.findAllByProject(project);
        List<ProjectFileImage> newImageList = createProjectFileImageList(fileList);
        projectFileImageRepository.deleteAll(oldImageList);
        for (ProjectFileImage image : newImageList)
            project.addProjectFileImage(image);

        // 변경사항 저장
        projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(Authentication auth, Long projectId) {
        String email = auth.getName();
        memberRepository.findByEmail(email);

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        project.delete();
    }
}
