package com.isfive.usearth.domain.board.dto;


import com.isfive.usearth.domain.board.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {

    private final Long id;

    private final String title;

    private final String writer;

    private final Integer views;

    private final LocalDateTime createDate;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        // TODO 추후 수정
        this.writer = "작성자";
        this.views = post.getViews();
        this.createDate = post.getCreatedDate();
    }
}
