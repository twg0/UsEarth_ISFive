package com.isfive.usearth.domain.project;

import com.isfive.usearth.domain.project.entity.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

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
}
