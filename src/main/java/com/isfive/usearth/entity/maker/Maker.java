package com.isfive.usearth.entity.maker;

import com.isfive.usearth.entity.Member;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Maker {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
//    private String profileImage;
    private String email;
    private String phone;
//    private String submitFile;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
