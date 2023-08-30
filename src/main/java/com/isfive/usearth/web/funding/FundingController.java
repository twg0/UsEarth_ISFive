package com.isfive.usearth.web.funding;

import com.isfive.usearth.domain.funding.service.FundingService;
import com.isfive.usearth.web.funding.dto.FundingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FundingController {

    private final FundingService fundingService;

    @PostMapping("/funding")
    public void funding(Authentication auth, @RequestBody FundingRequest request) {
        fundingService.funding(auth.getName(), request.toDeliveryRegister(), request.toRewardSkuRegisters());
    }
}
