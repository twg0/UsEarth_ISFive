package com.isfive.usearth.domain.maker.dto;

import com.isfive.usearth.domain.maker.entity.*;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Builder
@Getter
public class MakerResponse {

    private String name;

    private String profileImage;

    private String phone;

    private String email;

    private String corporateSealCertificate;

    private BusinessInformation businessInformation;

    private String dType;


    public static MakerResponse fromEntity(Maker maker) {
        if (maker instanceof Individual) {
            return fromIndividual((Individual) maker);
        } else if (maker instanceof PersonalBusiness) {
            return fromPersonalBusiness((PersonalBusiness) maker);
        } else if (maker instanceof CorporateBusiness) {
            return fromCorporateBusiness((CorporateBusiness) maker);
        }

        throw new RuntimeException();
    }

    private static MakerResponse fromIndividual(Individual maker) {
        return createDefaultMakerBuilder(maker)
                .dType("Individual")
                .build();
    }

    private static MakerResponse fromPersonalBusiness(PersonalBusiness maker) {
        return createDefaultMakerBuilder(maker)
                .businessInformation(maker.getBusinessInformation())
                .dType("PersonalBusiness")
                .build();
    }

    private static MakerResponse fromCorporateBusiness(CorporateBusiness maker) {
        return createDefaultMakerBuilder(maker)
                .businessInformation(maker.getBusinessInformation())
                .corporateSealCertificate(maker.getCorporateSealCertificate())
                .dType("CorporateBusiness")
                .build();
    }


    private static MakerResponseBuilder createDefaultMakerBuilder(Maker maker) {
        return MakerResponse.builder()
                .name(maker.getName())
                .profileImage(maker.getProfileImage())
                .phone(maker.getPhone())
                .email(maker.getEmail());


    }
}
