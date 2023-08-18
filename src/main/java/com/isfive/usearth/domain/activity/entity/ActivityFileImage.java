package com.isfive.usearth.domain.activity.entity;

import com.isfive.usearth.domain.common.FileImage;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivityFileImage{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activity;

    @Embedded
    private FileImage fileImage;
}
