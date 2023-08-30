package com.isfive.usearth.domain.project.dto;

import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.service.RewardService;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RewardsResponse {
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private String expectedSendDate;
    private Integer deliveryFee;
    private Integer stock;
    private Integer initStock;
    private List<RewardSkuResponse> rewardSkuResponses = new ArrayList<>();

    public RewardsResponse(Reward reward) {
        this.id = reward.getId();
        this.title = reward.getTitle();
        this.description = reward.getDescription();
        this.price = reward.getPrice();
        this.expectedSendDate = reward.getExpectedSendDate();
        this.deliveryFee = reward.getDeliveryFee();
        this.stock = reward.getStock();
        this.initStock = reward.getInitStock();
    }

    public void addRewardSkuResponses(RewardSkuResponse rewardSkuResponse) {
        rewardSkuResponses.add(rewardSkuResponse);
    }
}