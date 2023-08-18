package com.isfive.usearth.domain.board.controller.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRequest {

    private String title;

    private String content;

    @Builder
    private PostCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
