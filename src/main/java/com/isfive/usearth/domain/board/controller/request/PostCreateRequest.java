package com.isfive.usearth.domain.board.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateRequest {

    private String title;

    private String content;

    @Builder
    private PostCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
