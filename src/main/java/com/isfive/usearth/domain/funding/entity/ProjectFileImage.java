package com.isfive.usearth.domain.funding.entity;

import com.isfive.usearth.domain.common.FileImage;

import jakarta.persistence.*;

@Entity
public class ProjectFileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private FileImage fileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
}
