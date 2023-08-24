package com.isfive.usearth.web.board;

import com.isfive.usearth.domain.board.service.PostCommentService;
import com.isfive.usearth.web.board.dto.PostCommentCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostCommentController {

    private final PostCommentService postCommentService;

    @PostMapping("/posts/{postId}/comments")
    public void writeComment(@PathVariable Long postId, @RequestBody PostCommentCreateRequest request) {
        postCommentService.createComment(postId, request.getContent(), "other");
    }
}
