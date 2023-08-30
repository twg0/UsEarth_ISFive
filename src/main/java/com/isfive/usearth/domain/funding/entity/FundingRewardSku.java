package com.isfive.usearth.domain.funding.entity;

import com.isfive.usearth.domain.project.entity.RewardSku;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingRewardSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_sku_id")
    private RewardSku rewardSku;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    private Integer count;

    private Integer price;

    @Builder
    public FundingRewardSku(RewardSku rewardSku, Integer count, Integer price) {
        this.rewardSku = rewardSku;
        this.count = count;
        this.price = price;
    }


    public static FundingRewardSku createFundingRewardSku(RewardSku rewardSku, Integer count, Integer price) {
        rewardSku.removeStock(count);
        return FundingRewardSku.builder()
                .rewardSku(rewardSku)
                .count(count)
                .price(price)
                .build();
    }

    public void setFunding(Funding funding) {
        this.funding = funding;
    }
}
