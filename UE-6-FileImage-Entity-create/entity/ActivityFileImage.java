package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Activity")
public class ActivityFileImage extends FileImage {

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Activity activity;
}
