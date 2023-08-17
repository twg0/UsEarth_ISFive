package com.isfive.usearth.domain.maker.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class CorporateBusiness extends Maker {

    @Embedded
    private BusinessInformation businessInformation;

    private String CorporateSealCertificate;
}
