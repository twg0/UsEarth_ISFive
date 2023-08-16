package com.isfive.usearth.entity;

import jakarta.persistence.*;

@Embeddable
public class FileImage {

    private String originalName;
    private String storedName;
}
