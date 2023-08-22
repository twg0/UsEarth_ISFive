package com.isfive.usearth.web.board;

import com.isfive.usearth.domain.board.dto.PostResponse;
import com.isfive.usearth.domain.board.service.PostLikeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isfive.usearth.domain.board.dto.PostsResponse;
import com.isfive.usearth.domain.board.service.PostService;
import com.isfive.usearth.web.board.dto.PostCreateRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostLikeService postLikeService;

    @PostMapping("/boards/{boardId}/posts")
    public void writePost(@PathVariable Long boardId, @RequestBody PostCreateRequest request) {
        postService.createPost(boardId, "temp", request.getTitle(), request.getContent());
    }

    @GetMapping("/boards/{boardId}/posts")
    public ResponseEntity<Page<PostsResponse>> findPosts(@PathVariable Long boardId,
                                         @RequestParam(defaultValue = "1") Integer page) {
        Page<PostsResponse> postsResponses = postService.readPosts(boardId, page);
        return new ResponseEntity<>(postsResponses, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponse> findPost(@PathVariable Long postId) {
        PostResponse response = postService.readPost(postId);
        boolean likedByUser = postLikeService.isPostLikedByUser(postId, "other");
        response.setLikedByUser(likedByUser);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/posts/{postId}/like")
    public void like(@PathVariable Long postId) {
        postLikeService.clickLike(postId, "other");
    }
}
