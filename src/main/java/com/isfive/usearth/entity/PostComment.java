package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Post post;
}
