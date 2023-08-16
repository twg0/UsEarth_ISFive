package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
public class PostFileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private FileImage fileImage;


}
