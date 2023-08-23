package com.isfive.usearth.domain.maker.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalBusiness extends Maker {
    @Embedded
    private BusinessInformation businessInformation;

   @Builder
   public PersonalBusiness(String name, String profileImage, String email, String phone, String submitFile, BusinessInformation businessInformation) {
       super(name, profileImage, email, phone, submitFile);
       this.businessInformation = businessInformation;
   }
}

