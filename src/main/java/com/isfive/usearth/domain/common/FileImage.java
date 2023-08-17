package com.isfive.usearth.domain.common;

import jakarta.persistence.*;

@Embeddable
public class FileImage {
    private String originalName;
    private String storedName;
}
