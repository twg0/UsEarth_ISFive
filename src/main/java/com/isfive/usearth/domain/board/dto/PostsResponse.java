package com.isfive.usearth.domain.board.dto;

import java.time.LocalDateTime;

import com.isfive.usearth.domain.board.entity.Post;

import lombok.Data;

@Data
public class PostsResponse {

    private final Long id;

    private final String title;

    private final String writer;

    private final Integer views;

    private final LocalDateTime createdDate;

    private boolean likedByUser;

    public PostsResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriterNickname();
        this.views = post.getViews();
        this.createdDate = post.getCreatedDate();
        this.likedByUser = false;
    }
}
