package com.isfive.usearth.domain.maker.entity;

import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Individual extends Maker {
    private String IDImage;
}
