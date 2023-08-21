package com.isfive.usearth.domain.maker.entity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// @SuperBuilder
public class Individual extends Maker {
    private String idCard;
   @Builder
   public Individual(String name, String profileImage, String email, String phone, String submitFile, String idCard) {
       super(name, profileImage, email, phone, submitFile);
       this.idCard = idCard;
   }
}
