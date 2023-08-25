package com.isfive.usearth.web.maker.dto.register_request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusinessMakerRequest extends MakerRegisterRequest{
    private String registrationNumber;
    private String corporateName;
}
