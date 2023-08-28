package com.isfive.usearth.web.board;

import com.isfive.usearth.domain.board.service.PostCommentService;
import com.isfive.usearth.web.board.dto.PostCommentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostCommentController {

    private final PostCommentService postCommentService;

    @PostMapping("/posts/{postId}/comments")
    public void writeComment(Authentication auth, @PathVariable Long postId, @RequestBody PostCommentCreateRequest request) {
        postCommentService.createComment(postId, request.getContent(), auth.getName());
    }

    @PostMapping("/comments/{commentId}/reply")
    public void writeReply(Authentication auth, @PathVariable Long commentId, @RequestBody PostCommentCreateRequest request) {
        postCommentService.createReply(commentId, request.getContent(), auth.getName());
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(Authentication auth, @PathVariable Long commentId) {
        postCommentService.deleteComment(commentId, auth.getName());
    }
}
