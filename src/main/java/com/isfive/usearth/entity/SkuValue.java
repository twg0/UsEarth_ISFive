package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class SkuValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    private OptionValue optionValue;
}
