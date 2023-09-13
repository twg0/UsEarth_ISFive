package com.isfive.usearth.domain.board.repository.post;

import com.isfive.usearth.domain.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostQueryRepository {

	Page<Post> findPosts(Long boardId, Pageable pageable);

	Post findByIdWithMember(Long postId);
}
