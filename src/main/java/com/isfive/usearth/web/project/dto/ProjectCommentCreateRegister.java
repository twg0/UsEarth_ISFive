package com.isfive.usearth.web.project.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectCommentCreateRegister {

    @NotBlank(message =  "댓글 내용은 필수입니다.")
    private String content;

    public ProjectCommentCreateRegister(String content) {
        this.content = content;
    }
}
