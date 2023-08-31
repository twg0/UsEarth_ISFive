package com.isfive.usearth.domain.project.repository;

import com.isfive.usearth.exception.EntityNotFoundException;
import com.isfive.usearth.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.project.entity.Project;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Page<Project> findAll(Pageable pageable);

    default Project findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.PROJECT_NOT_FOUND));
    }

    @Query("select distinct p from Project p join fetch p.rewards r")
    Optional<Project> findByIdWithRewards(Long projectId);
}
