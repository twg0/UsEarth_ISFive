package com.isfive.usearth.web.maker.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class MakerRegister {
    private String name;
    private MultipartFile profileImage;
    private String email;
    private String phone;
    private MultipartFile submitFile;
    private String makerType;
    private MultipartFile idCard;
    private String registrationNumber;
    private String corporateName;
    private MultipartFile registration;
    private MultipartFile corporateSealCertificate;

    public MakerRegister(MakerRegisterRequest request){
        this.name = request.getName();
        this.email = request.getEmail();
        this.profileImage = request.getProfileImage();
        this.phone = request.getPhone();
        this.submitFile = request.getSubmitFile();
        this.makerType = request.getMakerType();
        this.idCard = request.getIdCard();
        this.registrationNumber = request.getRegistrationNumber() != null ? request.getRegistrationNumber() : null ;
        this.corporateName = request.getCorporateName() != null ? request.getCorporateName() : null ;
        this.registration = request.getRegistration();
        this.corporateSealCertificate = request.getCorporateSealCertificate() != null ? request.getCorporateSealCertificate() : null;
    }

//    public Maker toEntity() {
//        return Maker.builder()
//                .name(this.name)
//                .email(this.email)
//                .profileImage(this.profileImage)
//                .phone(this.phone)
//                .submitFile(this.submitFile)
//                .build();
//    }

}
