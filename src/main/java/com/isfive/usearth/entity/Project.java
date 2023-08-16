package com.isfive.usearth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    private String repImage; // 대표이미지

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Maker maker;

//    private Period fundingDate;

//    private List<Tag> searchTags;
//    private List<Reward> rewards;
//    private List<FileImage> projectImages;
//    private List<ProjectComment> comments;
//    private List<ProjectLike> likes;
}
