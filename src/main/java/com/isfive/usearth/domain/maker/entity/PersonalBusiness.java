package com.isfive.usearth.domain.maker.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalBusiness extends Maker {

    @Embedded
    private BusinessInformation businessInformation;
}

