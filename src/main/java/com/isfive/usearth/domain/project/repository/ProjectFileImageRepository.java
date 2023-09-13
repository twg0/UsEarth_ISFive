package com.isfive.usearth.domain.project.repository;

import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.entity.ProjectFileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectFileImageRepository extends JpaRepository<ProjectFileImage, Long> {
	List<ProjectFileImage> findAllByProject(Project project);
}
