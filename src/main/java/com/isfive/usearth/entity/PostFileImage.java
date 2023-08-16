package com.isfive.usearth.entity;

import com.isfive.usearth.Post;
import jakarta.persistence.*;

@Entity
public class PostFileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private FileImage fileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;


}
