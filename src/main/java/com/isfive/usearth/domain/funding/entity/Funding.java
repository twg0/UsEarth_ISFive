package com.isfive.usearth.domain.funding.entity;

import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.member.entity.Member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.isfive.usearth.domain.funding.entity.FundingStatus.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private FundingStatus status;

    @OneToMany(mappedBy = "funding", cascade = CascadeType.ALL)
    private List<FundingRewardSku> fundingRewardSkus = new ArrayList<>();

    @Builder
    private Funding(Member member, Delivery delivery, FundingStatus status) {
        this.member = member;
        this.delivery = delivery;
        this.status = status;
    }

    public static Funding createFunding(Member member, Delivery delivery, List<FundingRewardSku> fundingRewardSkus) {
        Funding funding = Funding.builder()
                .member(member)
                .delivery(delivery)
                .status(ORDER)
                .build();
        fundingRewardSkus.forEach(funding::addFundingRewardSku);
        return funding;
    }

    private void addFundingRewardSku(FundingRewardSku fundingRewardSku) {
        fundingRewardSkus.add(fundingRewardSku);
        fundingRewardSku.setFunding(this);
    }
}
