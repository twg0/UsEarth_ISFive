package com.isfive.usearth.domain.maker.entity;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Embeddable
@Getter
public class BusinessInformation {

    private String registrationNumber;

    private String corporateName;

    private String registration;

    @Builder
    public BusinessInformation(String registrationNumber, String corporateName, String registration) {
        this.registrationNumber = registrationNumber;
        this.corporateName = corporateName;
        this.registration = registration;
    }

}
