package com.isfive.usearth.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 색상, 사이즈

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id")
    private Reward reward;

    @OneToMany(mappedBy = "option")
    private List<OptionValue> optionValues;
}
