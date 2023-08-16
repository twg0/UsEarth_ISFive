package com.isfive.usearth;

import jakarta.persistence.*;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128)
    private String title;
    private String content;
    private String organization;
    private String place;
    private Integer maxMember;
    @Embedded
    private Period applicateDate;
    @Embedded
    private Period activityDate;
    @Enumerated(EnumType.STRING)
    private Permit status;

//    @OneToMany(mappedBy = "activity")
//    private List<FileImage> images = new ArrayList<>();

//    @OneToMany(mappedBy = "activity")
//    private List<Member> members = new ArrayList<>();

}
