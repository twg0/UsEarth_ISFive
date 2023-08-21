package com.isfive.usearth.domain.project.entity;

import com.isfive.usearth.domain.funding.entity.FundingReward;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer price;

    private Integer initStock; // 초기 재고

    private String expectedSendDate;

    private Integer deliveryFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "reward")
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "reward")
    private List<RewardSku> rewardSku = new ArrayList<>();
}
