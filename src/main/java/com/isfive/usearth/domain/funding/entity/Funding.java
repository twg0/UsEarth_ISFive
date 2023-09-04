package com.isfive.usearth.domain.funding.entity;

import static com.isfive.usearth.domain.funding.entity.FundingStatus.*;

import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.member.entity.Member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Delivery delivery;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Payment payment;

    @OneToMany(mappedBy = "funding", cascade = CascadeType.ALL)
    private List<FundingRewardSku> fundingRewardSkus = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FundingStatus status;

    @Builder
    private Funding(Member member, Delivery delivery, Payment payment, FundingStatus status) {
        this.member = member;
        this.delivery = delivery;
        this.payment = payment;
        this.status = status;
    }

    public static Funding createFunding(Member member, Delivery delivery, Payment payment, List<FundingRewardSku> fundingRewardSkus) {
        Funding funding = Funding.builder()
                .member(member)
                .delivery(delivery)
                .payment(payment)
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
