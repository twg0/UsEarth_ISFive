package com.isfive.usearth.entity;

import com.isfive.usearth.Period;
import com.isfive.usearth.entity.maker.Maker;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    private String story;

    private Integer targetAmount;

    @Embedded
    private FileImage repImage; // 대표이미지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id")
    private Maker maker;

    @Embedded
    private Period fundingDate;

    @OneToMany(mappedBy = "project")
    private List<Tag> searchTags = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Reward> rewards = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectFileImage> projectImages = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectLike> likes = new ArrayList<>();
}
