package com.isfive.usearth.domain.board.dto;

import com.isfive.usearth.domain.board.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {

    private final Long id;

    private final String title;

    private final String writer;

    private final String content;

    private final Integer views;

    private final LocalDateTime createdDate;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriterNickname();
        this.content = post.getContent();
        this.views = post.getViews();
        this.createdDate = post.getCreatedDate();
    }
}
