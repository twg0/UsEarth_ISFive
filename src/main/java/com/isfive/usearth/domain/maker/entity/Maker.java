package com.isfive.usearth.domain.maker.entity;

import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.web.maker.dto.MakerResponse;
import com.isfive.usearth.web.maker.dto.MakerUpdate;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

//    @Builder
//    public Maker(String name, String profileImage, String email, String phone, String submitFile) {
//        this.name = name;
//        this.profileImage = profileImage;
//        this.phone = phone;
//        this.email = email;
//        this.submitFile = submitFile;
//    }

    public void update(MakerUpdate makerUpdate) {
        this.phone = makerUpdate.getPhone();
        this.email = makerUpdate.getEmail();
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
    }
}
