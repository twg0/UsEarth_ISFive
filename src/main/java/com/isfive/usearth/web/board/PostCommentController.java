package com.isfive.usearth.web.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isfive.usearth.domain.board.service.PostCommentService;
import com.isfive.usearth.web.board.dto.PostCommentCreateRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostCommentController {

	private final PostCommentService postCommentService;

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<Void> writeComment(Authentication auth,
		@PathVariable Long postId,
		@RequestBody @Valid PostCommentCreateRequest request) {
		postCommentService.createComment(postId, request.getContent(), auth.getName());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/comments/{commentId}/reply")
	public ResponseEntity<Void> writeReply(Authentication auth,
		@PathVariable Long commentId,
		@RequestBody @Valid PostCommentCreateRequest request) {
		postCommentService.createReply(commentId, request.getContent(), auth.getName());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<Void> deleteComment(Authentication auth, @PathVariable Long commentId) {
		postCommentService.deleteComment(commentId, auth.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
