package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Post")
public class PostFileImage extends FileImage {

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Project project;
}
