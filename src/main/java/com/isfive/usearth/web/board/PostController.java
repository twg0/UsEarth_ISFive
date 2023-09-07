package com.isfive.usearth.web.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isfive.usearth.domain.board.dto.PostResponse;
import com.isfive.usearth.domain.board.dto.PostsResponse;
import com.isfive.usearth.domain.board.service.PostService;
import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.web.board.dto.PostCreateRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final FileImageService fileImageService;

	@PostMapping("/boards/{boardId}/posts")
	public ResponseEntity<Void> writePost(Authentication auth,
		@PathVariable Long boardId,
		@RequestPart @Valid PostCreateRequest request,
		@RequestPart(required = false) List<MultipartFile> postImages) {
		List<FileImage> fileImages = new ArrayList<>();
		if (postImages != null) {
			fileImages = fileImageService.createFileImageList(postImages);
		}
		postService.createPost(boardId, auth.getName(), request.getTitle(), request.getContent(), fileImages);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/boards/{boardId}/posts")
	public ResponseEntity<Page<PostsResponse>> findPosts(Authentication auth,
		@PathVariable Long boardId,
		@RequestParam(defaultValue = "1") Integer page) {
		Page<PostsResponse> postsResponses = postService.readPosts(boardId, page, auth.getName());
		return new ResponseEntity<>(postsResponses, HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostResponse> findPost(Authentication auth, @PathVariable Long postId) {
		PostResponse response = postService.readPost(postId, auth.getName());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/posts/{postId}/like")
	public ResponseEntity<Void> like(Authentication auth, @PathVariable Long postId) {
		postService.like(postId, auth.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
