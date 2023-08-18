package com.isfive.usearth.domain.board.entity;

import com.isfive.usearth.domain.common.FileImage;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostFileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private FileImage fileImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
