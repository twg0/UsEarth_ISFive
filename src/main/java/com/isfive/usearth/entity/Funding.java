package com.isfive.usearth.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private FundingStatus status;

    @OneToMany(mappedBy = "funding")
    private List<FundingReward> fundingRewards = new ArrayList<>();
}
