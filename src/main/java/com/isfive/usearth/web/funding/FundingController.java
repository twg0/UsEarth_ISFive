package com.isfive.usearth.web.funding;

import com.isfive.usearth.domain.funding.service.FundingService;
import com.isfive.usearth.web.funding.dto.FundingRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "4. Funding", description = "Funding API")
public class FundingController {

	private final FundingService fundingService;

	@Operation(summary = "펀딩 결제 예약")
	@PostMapping("/funding")
	public ResponseEntity<Void> funding(Authentication auth, @RequestBody @Valid FundingRequest request) {
		fundingService.funding(auth.getName(), request.toDeliveryRegister(), request.toPaymentRegister(),
			request.toRewardSkuRegisters());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "펀딩 결제 예약 취소")
	@DeleteMapping("/funding/{fundingId}")
	public ResponseEntity<Void> cancel(Authentication auth, @PathVariable("fundingId") Long fundingId) {
		fundingService.cancel(auth.getName(), fundingId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
