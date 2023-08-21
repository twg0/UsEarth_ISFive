package com.isfive.usearth.domain.maker.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
// @SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CorporateBusiness extends Maker {

    @Embedded
    private BusinessInformation businessInformation;
    private String corporateSealCertificate;

    @Builder
   public CorporateBusiness(String name, String profileImage, String email, String phone, String submitFile, BusinessInformation businessInformation, String corporateSealCertificate) {
       super(name, profileImage, email, phone, submitFile);
       this.businessInformation = businessInformation;
       this.corporateSealCertificate = corporateSealCertificate;
   }
}
