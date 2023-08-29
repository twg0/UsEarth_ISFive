package com.isfive.usearth.domain.project.service;

import com.isfive.usearth.domain.common.FileImage;

import com.isfive.usearth.domain.maker.entity.Maker;
import com.isfive.usearth.domain.maker.repository.MakerRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.project.dto.*;
import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.entity.ProjectFileImage;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.entity.Tag;
import com.isfive.usearth.domain.project.repository.ProjectFileImageRepository;
import com.isfive.usearth.domain.project.repository.ProjectRepository;

import com.isfive.usearth.domain.project.repository.TagRepository;
import com.isfive.usearth.exception.EntityNotFoundException;
import com.isfive.usearth.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Member member = memberRepository.findByUsernameOrThrow(username);

        Project project = projectCreate.toEntity(member);
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

    @Transactional
    public void updateProject(String username, Long projectId, ProjectUpdate projectUpdate, List<FileImage> fileList) {
        Member member = memberRepository.findByUsernameOrThrow(username);

        Project project = projectRepository.findByIdOrElseThrow(projectId);

        if (!member.equals(project.getMember()))
            new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND);

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
    public void deleteProject(
            Authentication auth, Long projectId) {
        String username = auth.getName();
        Member member = memberRepository.findByUsernameOrThrow(username);
        Project project = projectRepository.findByIdOrElseThrow(projectId);

        if (!member.equals(project.getMember()))
            new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND);

        project.delete();
    }

    public Page<ProjectsResponse> readProjects(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return createProjectResponsePage(pageable, projects);
    }

    public ProjectResponse readProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        return new ProjectResponse(project);
    }

    private List<ProjectFileImage> createProjectFileImageList(List<FileImage> fileImageList) {
        List<ProjectFileImage> projectFileImageList = new ArrayList<>();
        for (FileImage file : fileImageList) {
            projectFileImageList.add(createProjectFileImage(file));
        }
        projectFileImageRepository.saveAll(projectFileImageList);
        return projectFileImageList;
    }

    private ProjectFileImage createProjectFileImage(FileImage fileImage) {
        ProjectFileImage projectFileImage = ProjectFileImage.builder()
                .fileImage(fileImage)
                .build();
        projectFileImageRepository.save(projectFileImage);
        return projectFileImage;
    }

    private Page<ProjectsResponse> createProjectResponsePage(Pageable pageable, Page<Project> projects) {
        List<ProjectsResponse> list = projects.stream()
                .map(ProjectsResponse::new)
                .toList();
        return new PageImpl<>(list, pageable, projects.getTotalElements());
    }
}
