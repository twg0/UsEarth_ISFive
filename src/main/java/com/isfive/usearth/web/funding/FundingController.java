package com.isfive.usearth.web.funding;

import com.isfive.usearth.web.funding.dto.FundingRequest;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundingController {

    @PostMapping("/funding")
    public void funding(Authentication auth, @RequestBody FundingRequest request) {

    }
}
