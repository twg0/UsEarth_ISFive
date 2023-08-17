package com.isfive.usearth.domain.funding.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class RewardSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reward reward;

    @OneToMany(mappedBy = "rewardSku")
    private List<SkuValue> skuValues = new ArrayList<>();
}
