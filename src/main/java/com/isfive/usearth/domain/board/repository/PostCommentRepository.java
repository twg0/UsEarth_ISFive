package com.isfive.usearth.domain.board.repository;

import com.isfive.usearth.domain.board.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}
