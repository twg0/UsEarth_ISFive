package com.isfive.usearth.entity.maker;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class PersonalBusiness extends Maker {

    @Embedded
    private BusinessInformation businessInformation;

//    private String registration;
}

