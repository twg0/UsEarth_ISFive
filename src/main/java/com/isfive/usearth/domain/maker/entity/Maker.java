package com.isfive.usearth.domain.maker.entity;

import com.isfive.usearth.domain.member.entity.Member;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Maker {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String profileImage;

    private String email;

    private String phone;

    private String submitFile;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
