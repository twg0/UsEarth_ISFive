package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @Embedded
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    private Funding funding;


}
