package com.isfive.usearth.domain.funding.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.member.entity.Member;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
