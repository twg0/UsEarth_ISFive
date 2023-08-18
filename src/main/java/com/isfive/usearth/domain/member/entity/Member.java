package com.isfive.usearth.domain.member.entity;

import com.isfive.usearth.domain.activity.entity.Activity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(
    indexes = @Index(name = "idx_member_username", columnList = "username")
)
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

    @Builder
    private Member(String email, String username, String nickname, String phone, String provider, Role role) {
        this.email = email;
        this.username = username;
        this.nickname = nickname;
        this.phone = phone;
        this.provider = provider;
        this.role = role;
    }
}
