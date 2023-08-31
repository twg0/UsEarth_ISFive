package com.isfive.usearth.web.funding.dto;

import java.util.List;

import com.isfive.usearth.domain.funding.dto.DeliveryRegister;
import com.isfive.usearth.domain.funding.dto.RewardSkuRegister;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FundingRequest {

    private DeliveryRequest deliveryRequest;

    private List<RewardSkuRequest> rewardSkuRequests;

    public DeliveryRegister toDeliveryRegister() {
        return deliveryRequest.toServiceDto();
    }

    public List<RewardSkuRegister> toRewardSkuRegisters() {
        return rewardSkuRequests.stream()
                .map(RewardSkuRequest::toServiceDto)
                .toList();
    }
}
