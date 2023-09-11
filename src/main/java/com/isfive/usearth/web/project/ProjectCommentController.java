package com.isfive.usearth.web.project;

import com.isfive.usearth.domain.project.service.ProjectCommentService;
import com.isfive.usearth.web.project.dto.ProjectCommentCreateRegister;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectCommentController {

    private final ProjectCommentService projectCommentService;

    @PostMapping("/projects/{projectId}/comments")
    public ResponseEntity<Void> writeComment(Authentication auth,
                                             @PathVariable Long projectId,
                                             @RequestBody @Valid ProjectCommentCreateRegister request) {
        projectCommentService.createComment(projectId, request.getContent(), auth.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/project-comments/{commentId}/reply")
    public ResponseEntity<Void> writeReply(Authentication auth,
                                           @PathVariable Long commentId,
                                           @RequestBody @Valid ProjectCommentCreateRegister request) {
        projectCommentService.createReply(commentId, request.getContent(), auth.getName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/project-comments/{commentId}")
    public ResponseEntity<Void> deleteComment(Authentication auth,
                                              @PathVariable Long commentId) {
        projectCommentService.deleteComment(commentId, auth.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
