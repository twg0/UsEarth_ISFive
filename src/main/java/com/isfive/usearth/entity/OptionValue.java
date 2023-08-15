package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class OptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Option option;
}
