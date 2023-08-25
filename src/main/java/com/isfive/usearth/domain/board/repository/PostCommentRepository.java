package com.isfive.usearth.domain.board.repository;

import com.isfive.usearth.domain.board.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    @Query("select pc from PostComment pc join fetch pc.post where pc.id = :id")
    Optional<PostComment> findByIdWithPost(@Param("id") Long id);
}
