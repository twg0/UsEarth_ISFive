package com.isfive.usearth.web.maker.dto;

import com.isfive.usearth.domain.maker.entity.Maker;
import com.isfive.usearth.domain.maker.entity.MakerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class MakerRegisterRequest {

    @NotBlank(message = "메이커명을 입력해주세요.")
    private String name;

//    @NotNull(message = "메이커 프로필 이미지를 등록해주세요.")
    private MultipartFile profileImage;

    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phone;

    private MultipartFile submitFile;

    private String makerType;

    private MultipartFile idCard;

    private String registrationNumber;

    private String corporateName;

    private MultipartFile registration;

    private MultipartFile corporateSealCertificate;


//    public Maker toEntity() {
//
//        return Maker.builder()
//                .name(this.name)
//                .phone(this.phone)
//                .email(this.email)
//                .build();
//    }

}
