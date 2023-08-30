package com.isfive.usearth.domain.funding.service;

import com.isfive.usearth.domain.funding.dto.DeliveryRegister;
import com.isfive.usearth.domain.funding.dto.RewardSkuRegister;
import com.isfive.usearth.domain.funding.entity.Delivery;
import com.isfive.usearth.domain.funding.entity.Funding;
import com.isfive.usearth.domain.funding.entity.FundingRewardSku;
import com.isfive.usearth.domain.funding.repository.FundingRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.project.dto.RewardsResponse;
import com.isfive.usearth.domain.project.entity.RewardSku;
import com.isfive.usearth.domain.project.repository.RewardSkuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FundingService {

    private final MemberRepository memberRepository;
    private final RewardSkuRepository rewardSkuRepository;
    private final FundingRepository fundingRepository;

    @Transactional
    public void funding(String username, DeliveryRegister deliveryRegister, List<RewardSkuRegister> rewardSkuRegisters) {
        Member member = memberRepository.findByUsernameOrThrow(username);

        Delivery delivery = Delivery.createDelivery(deliveryRegister);

        Map<Long, Integer> idCountMap = rewardSkuRegisters.stream()
                .collect(Collectors.toMap(RewardSkuRegister::getRewardSkuId, RewardSkuRegister::getCount));

        List<RewardSku> rewardSkus = rewardSkuRepository.findAllByIdWithReward(idCountMap.keySet());

        List<FundingRewardSku> fundingRewardSkus = rewardSkus.stream()
                .map(rewardSku -> {
                    Integer count = idCountMap.get(rewardSku.getId());
                    Integer price = rewardSku.getPrice();
                    return FundingRewardSku.createFundingRewardSku(rewardSku, count, price);
                })
                .toList();

        Funding funding = Funding.createFunding(member, delivery, fundingRewardSkus);
        fundingRepository.save(funding);
    }
}
