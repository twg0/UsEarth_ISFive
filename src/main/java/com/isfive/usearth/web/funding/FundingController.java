package com.isfive.usearth.web.funding;

import com.isfive.usearth.domain.funding.service.FundingService;
import com.isfive.usearth.web.funding.dto.FundingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FundingController {

    private final FundingService fundingService;

    @PostMapping("/funding")
    public void funding(Authentication auth, @RequestBody @Valid FundingRequest request) {
        fundingService.funding(auth.getName(), request.toDeliveryRegister(), request.toPaymentRegister(), request.toRewardSkuRegisters());
    }

    @DeleteMapping("/funding/{fundingId}")
    public void cancel(Authentication auth, @PathVariable Long fundingId) {
        fundingService.cancel(auth.getName(), fundingId);
    }
}
