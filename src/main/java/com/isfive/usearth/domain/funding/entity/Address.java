package com.isfive.usearth.domain.funding.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String detail;

    private String zipcode;
}
