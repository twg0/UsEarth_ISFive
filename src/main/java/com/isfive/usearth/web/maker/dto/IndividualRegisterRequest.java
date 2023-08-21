package com.isfive.usearth.web.maker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
@Getter
public class IndividualRegisterRequest extends MakerRegisterRequest{
    private MultipartFile idCard;
}
