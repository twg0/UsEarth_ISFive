package com.isfive.usearth.domain.project.entity;

import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.funding.entity.FundingRewardSku;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "rewardSku")
    @Builder.Default
    private List<SkuValue> skuValues = new ArrayList<>();

    @OneToMany(mappedBy = "rewardSku")
    @Builder.Default
    private List<FundingRewardSku> fundingRewardSkus = new ArrayList<>();

    public void setReward(Reward reward) {
        this.reward = reward;
        if (!reward.getRewardSkus().contains(this))
            reward.getRewardSkus().add(this);
    }

}
