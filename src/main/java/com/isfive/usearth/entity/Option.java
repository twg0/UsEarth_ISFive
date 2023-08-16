package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 색상, 사이즈

    @ManyToOne(fetch = FetchType.LAZY)
    private Reward reward;

//    private List<OptionValue> optionValues;
}
