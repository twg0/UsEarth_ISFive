package com.isfive.usearth.web.funding;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.isfive.usearth.domain.funding.service.FundingService;
import com.isfive.usearth.web.funding.dto.FundingRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FundingController {

    private final FundingService fundingService;

    @PostMapping("/funding")
    public ResponseEntity<Void> funding(Authentication auth, @RequestBody @Valid FundingRequest request) {
        fundingService.funding(auth.getName(), request.toDeliveryRegister(), request.toPaymentRegister(), request.toRewardSkuRegisters());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/funding/{fundingId}")
    public ResponseEntity<Void> cancel(Authentication auth, @PathVariable Long fundingId) {
        fundingService.cancel(auth.getName(), fundingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
