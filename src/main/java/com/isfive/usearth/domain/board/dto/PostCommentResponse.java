package com.isfive.usearth.domain.board.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.board.entity.PostComment;

import lombok.Data;

@Data
public class PostCommentResponse {

    private Long id;
    private String content;
    private String writer;
    private boolean delete;
    private LocalDateTime createDate;
    private List<PostCommentResponse> postCommentResponses = new ArrayList<>();

    public PostCommentResponse(PostComment postComment) {
        this.id = postComment.getId();
        this.content = postComment.getContent();
        this.writer = postComment.getMember().getUsername();
        this.delete = postComment.isDelete();
        this.createDate = postComment.getCreatedDate();
    }

    public void addPostCommentResponses(PostCommentResponse postCommentResponse) {
        postCommentResponses.add(postCommentResponse);
    }
}
