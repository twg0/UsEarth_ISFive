package com.isfive.usearth.domain.project.dto;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.entity.Reward;
import lombok.Data;

import java.util.List;

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

    @Data
    private static class RewardsResponse {
        private String title;
        private String description;
        private Integer price;
        private String expectedSendDate;
        private Integer deliveryFee;
        private Integer stock;
        private Integer initStock;

        public RewardsResponse(Reward reward) {
            this.title = reward.getTitle();
            this.description = reward.getDescription();
            this.price = reward.getPrice();
            this.expectedSendDate = reward.getExpectedSendDate();
            this.deliveryFee = reward.getDeliveryFee();
            this.stock = reward.getStock();
            this.initStock = reward.getInitStock();
        }
    }

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
