package com.isfive.usearth.web.funding.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FundingRequest {

    private DeliveryRequest deliveryRequest;

    private List<RewardSkuRequest> rewardSkuRequests;
}
