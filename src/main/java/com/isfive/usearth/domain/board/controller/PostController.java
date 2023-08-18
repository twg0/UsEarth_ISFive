package com.isfive.usearth.domain.board.controller;

import com.isfive.usearth.domain.board.controller.request.PostCreateRequest;
import com.isfive.usearth.domain.board.dto.PostResponse;
import com.isfive.usearth.domain.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/boards/{boardId}/posts")
    public void writePost(@PathVariable Long boardId, PostCreateRequest request) {
        postService.createPost(boardId, "temp", request.getTitle(), request.getContent());
    }

    @GetMapping("/boards/{boardId}/posts")
    public Page<PostResponse> findPosts(@PathVariable Long boardId,
                                        @RequestParam Integer page) {
        return postService.readPosts(boardId, page);
    }
}
