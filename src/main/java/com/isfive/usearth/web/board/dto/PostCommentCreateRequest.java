package com.isfive.usearth.web.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCommentCreateRequest {

    private String content;

    public PostCommentCreateRequest(String content) {
        this.content = content;
    }
}
