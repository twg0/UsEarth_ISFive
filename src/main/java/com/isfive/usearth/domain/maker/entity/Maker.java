package com.isfive.usearth.domain.maker.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.web.maker.dto.MakerUpdate;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

   public Maker(String name, String profileImage, String email, String phone, String submitFile) {
       this.name = name;
       this.profileImage = profileImage;
       this.phone = phone;
       this.email = email;
       this.submitFile = submitFile;
   }

    public void update(MakerUpdate makerUpdate) {
       if (makerUpdate.getPhone() != null) {
           this.phone = makerUpdate.getPhone();
       }
       if (makerUpdate.getEmail() != null) {
           this.email = makerUpdate.getEmail();
       }
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "maker")
    List<Project> projects = new ArrayList<>();

}
