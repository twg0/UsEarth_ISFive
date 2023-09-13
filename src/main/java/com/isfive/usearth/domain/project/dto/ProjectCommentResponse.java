package com.isfive.usearth.domain.project.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.project.entity.ProjectComment;

import lombok.Data;

@Data
public class ProjectCommentResponse {

    private Long id;
    private String content;
    private String writer;
    private boolean delete;
    private LocalDateTime createDate;
    private List<ProjectCommentResponse> projectCommentResponses = new ArrayList<>();

    public ProjectCommentResponse(ProjectComment projectComment) {
        this.id = projectComment.getId();
        this.content = projectComment.getContent();
        this.writer = projectComment.getMember().getUsername();
        this.delete = projectComment.isDeleted();
        this.createDate = projectComment.getCreatedDate();
    }

    public void addProjectCommentResponses(ProjectCommentResponse projectCommentResponse) {
        projectCommentResponses.add(projectCommentResponse);
    }
}
