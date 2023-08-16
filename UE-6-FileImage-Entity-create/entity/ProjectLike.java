package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class ProjectLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Product product;
}
