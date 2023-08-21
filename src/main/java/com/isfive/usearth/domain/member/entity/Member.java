package com.isfive.usearth.domain.member.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.isfive.usearth.domain.activity.entity.Activity;
import com.isfive.usearth.domain.common.BaseEntity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(
    indexes = @Index(name = "idx_member_email", columnList = "email")
)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    private String username;

    private String nickname;

    private String password;

    private String phone;

    private String refreshToken;

    // @Column(name = "provider", nullable = false)
    private String provider;

    public Member updateToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Member updateRole(Role role) {
        this.role = role;
        return this;
    }

    @PostConstruct
    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    /* 연관관계 */

    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activity;

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
