package com.isfive.usearth.domain.project.dto;

import java.util.List;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.project.entity.Project;

import lombok.Data;

@Data
public class ProjectResponse {

    private String title;
    private String summary;
    private String story;
    private Integer targetAmount;
    private Integer totalFundingAmount;
    private FileImage repImage;
    private String makerName;
    private String makerProfileImage;
    private Period fundingDate;
    private List<RewardsResponse> rewardsResponses;
    private List<FileImage> projectImages;
    private Integer likeCount;

    public ProjectResponse(Project project) {
        this.title = project.getTitle();
        this.summary = project.getSummary();
        this.story = project.getStory();
        this.targetAmount = project.getTargetAmount();
        this.totalFundingAmount = project.getTotalFundingAmount();
        this.repImage = project.getRepImage();
        this.makerName = project.getMakerName();
        this.makerProfileImage = project.getMaker().getProfileImage();
        this.fundingDate = project.getFundingDate();
        this.rewardsResponses = project.getRewards().stream()
                .map(RewardsResponse::new)
                .toList();
        this.projectImages = project.getProjectFileImages();
        this.likeCount = project.getProjectLikeCount();
    }
}
