package com.isfive.usearth.domain.project.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.maker.repository.MakerRepository;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.project.dto.ProjectCreate;
import com.isfive.usearth.domain.project.dto.ProjectResponse;
import com.isfive.usearth.domain.project.dto.RewardCreate;
import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.entity.ProjectFileImage;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.entity.Tag;
import com.isfive.usearth.domain.project.repository.ProjectFileImageRepository;
import com.isfive.usearth.domain.project.repository.ProjectRepository;
import com.isfive.usearth.domain.project.repository.RewardRepository;
import com.isfive.usearth.domain.project.repository.TagRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final MakerRepository makerRepository;
    private final RewardRepository rewardRepository;
    private final TagRepository tagRepository;
    private final ProjectFileImageRepository projectFileImageRepository;
    private final RewardService rewardService;
    private final TagService tagService;
    private final FileImageService fileImageService;

    @Transactional
    public void createProject(ProjectCreate projectCreate, List<RewardCreate> rewardCreateList, List<FileImage> fileList) throws IOException {
        Project project = projectCreate.toEntity();
        projectRepository.save(project);

        // 대표 이미지 등록
        FileImage fileImage = fileImageService.createFileImage(projectCreate.getRepImage());
        project.setRepImage(fileImage);

        // 메이커 등록
//        Maker maker = makerRepository.findByName(projectCreate.getMakerName());
//        project.setMaker(maker);

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
}
