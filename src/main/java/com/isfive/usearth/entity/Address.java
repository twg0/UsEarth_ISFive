package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Embeddable
public class Address {

    private String detail;

    private String zipcode;
}
