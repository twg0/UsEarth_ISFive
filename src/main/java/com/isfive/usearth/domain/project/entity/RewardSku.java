package com.isfive.usearth.domain.project.entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Reward reward;

    @OneToMany(mappedBy = "rewardSku")
    private List<SkuValue> skuValues = new ArrayList<>();

    public void setReward(Reward reward) {
        this.reward = reward;
        reward.getRewardSkus().add(this);
    }
}
