package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Project")
public class ProjectFileImage extends FileImage {

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Project project;
}
