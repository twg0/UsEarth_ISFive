package com.isfive.usearth.entity;

import com.isfive.usearth.Activity;
import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activity;

    private String email;

    private String username;

    private String nickname;

    private String phone;

    private String provider;

    @Enumerated(EnumType.STRING)
    private Role role;
}
