package com.isfive.usearth.web.maker.dto.register_request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
@Getter
public class PersonalRegisterRequest extends MakerRegisterRequest {
    private String registrationNumber;

    private String corporateName;

    private MultipartFile registration;
}
