package com.isfive.usearth.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Reward reward;

//    private List<OptionValue> optionValues;
}
