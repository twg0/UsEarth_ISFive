package com.isfive.usearth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer price;

    private Integer stock;

    private Integer initStock;

    private String expectedSendDate;

    private Integer deliveryFee;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Project project;

//    private List<Option> options;
}
