package com.isfive.usearth.web.board;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isfive.usearth.domain.board.service.PostCommentService;
import com.isfive.usearth.web.board.dto.PostCommentCreateRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostCommentController {

    private final PostCommentService postCommentService;

    @PostMapping("/posts/{postId}/comments")
    public void writeComment(Authentication auth,
                             @PathVariable Long postId,
                             @RequestBody @Valid PostCommentCreateRequest request) {
        postCommentService.createComment(postId, request.getContent(), auth.getName());
    }

    @PostMapping("/comments/{commentId}/reply")
    public void writeReply(Authentication auth,
                           @PathVariable Long commentId,
                           @RequestBody @Valid PostCommentCreateRequest request) {
        postCommentService.createReply(commentId, request.getContent(), auth.getName());
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(Authentication auth, @PathVariable Long commentId) {
        postCommentService.deleteComment(commentId, auth.getName());
    }
}
