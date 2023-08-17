package com.isfive.usearth.domain.maker.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class PersonalBusiness extends Maker {

    @Embedded
    private BusinessInformation businessInformation;
}

