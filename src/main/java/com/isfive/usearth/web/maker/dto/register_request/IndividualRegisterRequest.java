package com.isfive.usearth.web.maker.dto.register_request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
@Getter
public class IndividualRegisterRequest extends MakerRegisterRequest{
    private MultipartFile idCard;
}
