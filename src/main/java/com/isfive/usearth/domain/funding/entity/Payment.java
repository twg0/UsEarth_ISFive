package com.isfive.usearth.domain.funding.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funding_id")
    private Funding funding;

    @Column(nullable = false, length = 19)
    private String cardNumber;

    @Column(nullable = false, length = 5)
    private String cardExpiry;

    @Column(nullable = false, length = 6)
    private String birth;

    @Column(nullable = false,length = 2)
    private String pwd_2digit;
}