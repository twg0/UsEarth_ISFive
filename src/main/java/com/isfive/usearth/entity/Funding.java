package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;

//    @Enumerated(EnumType.STRING)
//    private FundingStatus status;

//    private List<FundingReward> fundingRewards;
}
