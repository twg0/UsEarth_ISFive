package com.isfive.usearth.domain.board.controller;

import com.isfive.usearth.domain.board.controller.request.PostCreateRequest;
import com.isfive.usearth.domain.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/boards/{boardId}/posts")
    public void writePost(@PathVariable Long boardId, PostCreateRequest request) {
        postService.createPost(boardId, "temp", request.getTitle(), request.getContent());
    }
}
