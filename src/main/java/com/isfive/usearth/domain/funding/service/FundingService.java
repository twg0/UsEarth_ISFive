package com.isfive.usearth.domain.funding.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isfive.usearth.domain.funding.dto.DeliveryRegister;
import com.isfive.usearth.domain.funding.dto.RewardSkuRegister;
import com.isfive.usearth.domain.funding.entity.Delivery;
import com.isfive.usearth.domain.funding.entity.Funding;
import com.isfive.usearth.domain.funding.entity.FundingRewardSku;
import com.isfive.usearth.domain.funding.repository.FundingRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.project.entity.RewardSku;
import com.isfive.usearth.domain.project.repository.RewardSkuRepository;

import lombok.RequiredArgsConstructor;

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

        Map<Long, Integer> idCountMap = createidCountMap(rewardSkuRegisters);

        List<RewardSku> rewardSkus = rewardSkuRepository.findAllByIdWithReward(idCountMap.keySet());

        List<FundingRewardSku> fundingRewardSkus = createFindingRewardSkus(rewardSkus, idCountMap);

        Funding funding = Funding.createFunding(member, delivery, fundingRewardSkus);
        fundingRepository.save(funding);
    }

    private Map<Long, Integer> createidCountMap(List<RewardSkuRegister> rewardSkuRegisters) {
        return rewardSkuRegisters.stream()
                .collect(Collectors.toMap(RewardSkuRegister::getRewardSkuId, RewardSkuRegister::getCount));
    }

    private List<FundingRewardSku> createFindingRewardSkus(List<RewardSku> rewardSkus, Map<Long, Integer> idCountMap) {
        return rewardSkus.stream()
                .map(rewardSku -> {
                    Integer count = idCountMap.get(rewardSku.getId());
                    Integer price = rewardSku.getPrice();
                    return FundingRewardSku.createFundingRewardSku(rewardSku, count, price);
                })
                .toList();
    }
}
