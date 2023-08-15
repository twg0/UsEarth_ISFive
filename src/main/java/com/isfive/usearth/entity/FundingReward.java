package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class FundingReward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer optionCount;

    private Integer totalPrice;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private RewardSku rewardSku;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Funding funding;
}
