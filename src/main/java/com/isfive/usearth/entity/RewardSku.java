package com.isfive.usearth.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RewardSku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Reward reward;

    @OneToMany(mappedBy = "reward_sku")
    private List<SkuValue> skuValues;
}
