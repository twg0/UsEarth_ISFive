package com.isfive.usearth.domain.project.repository;

import com.isfive.usearth.domain.project.entity.ProjectComment;
import com.isfive.usearth.exception.EntityNotFoundException;
import com.isfive.usearth.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectCommentRepository extends JpaRepository<ProjectComment, Long> {

    default ProjectComment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new EntityNotFoundException(ErrorCode.PROJECT_NOT_FOUND));
    }

    @Query("select pc from ProjectComment pc join fetch pc.member where pc.project.id = :projectId")
    Page<ProjectComment> findAllByProject_Id(@Param("projectId") Long projectId, Pageable pageable);
}
