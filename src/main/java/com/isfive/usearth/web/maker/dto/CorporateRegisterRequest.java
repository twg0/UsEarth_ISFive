package com.isfive.usearth.web.maker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
@Getter
public class CorporateRegisterRequest extends MakerRegisterRequest{
    private String registrationNumber;

    private String corporateName;

    private MultipartFile registration;

    private MultipartFile corporateSealCertificate;

}
