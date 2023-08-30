package com.isfive.usearth.web.funding.dto;

import com.isfive.usearth.domain.funding.dto.RewardSkuRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RewardSkuRequest {

    private Long rewardSkuId;
    private Integer count;

    public RewardSkuRegister toServiceDto() {
        return new RewardSkuRegister(rewardSkuId, count);
    }
}

