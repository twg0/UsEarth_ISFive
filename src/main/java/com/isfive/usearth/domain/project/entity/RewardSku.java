package com.isfive.usearth.domain.project.entity;

import com.isfive.usearth.domain.funding.entity.FundingReward;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RewardSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stock;

    private Integer initStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id")
    private Reward reward;

    @OneToMany
    @Builder.Default
    private List<SkuValue> skuValues = new ArrayList<>();

    @OneToMany(mappedBy = "rewardSku")
    @Builder.Default
    private List<FundingReward> fundingRewards = new ArrayList<>();

    public void setReward(Reward reward) {
        this.reward = reward;
        if (!reward.getRewardSkus().contains(this))
            reward.getRewardSkus().add(this);
    }

}
