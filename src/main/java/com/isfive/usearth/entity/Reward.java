package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer price;

    private Integer initStock; // 초기 재고

    private String expectedSendDate;

    private Integer deliveryFee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

//    private List<Option> options;
}
