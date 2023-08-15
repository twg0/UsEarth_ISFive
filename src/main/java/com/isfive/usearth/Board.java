package com.isfive.usearth;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

//    @OneToMany(mappedBy = "board")
//    private List<Post> posts = new ArrayList<>();

}
