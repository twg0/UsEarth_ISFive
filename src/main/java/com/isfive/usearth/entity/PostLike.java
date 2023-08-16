package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class PostLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Post post;
}
