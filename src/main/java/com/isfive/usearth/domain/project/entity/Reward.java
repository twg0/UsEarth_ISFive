package com.isfive.usearth.domain.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer price;

    private String expectedSendDate;

    private Integer deliveryFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "reward")
    @Builder.Default
    private List<Option> options = new ArrayList<>();

    @OneToMany(mappedBy = "reward")
    private List<RewardSku> rewardSkus = new ArrayList<>();

    public void setProject(Project project) {
        this.project = project;
        if (!project.getRewards().contains(this))
            project.getRewards().add(this);
    }

    public void addOption(Option option) {
        this.getOptions().add(option);
        if (option.getReward() != this)
            option.setReward(this);
    }

}
