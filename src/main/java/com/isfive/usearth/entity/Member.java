package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String username;

    private String nickname;

    private String phone;

    private String provider;

    @Enumerated(EnumType.STRING)
    private Role role;
}
