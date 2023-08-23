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
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 색상, 사이즈

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_id")
    private Reward reward;

    @OneToMany(mappedBy = "option")
    @Builder.Default
    private List<OptionValue> optionValues = new ArrayList<>();

    @OneToMany(mappedBy = "option")
    @Builder.Default
    private List<SkuValue> skuValues = new ArrayList<>();

    public void setReward(Reward reward) {
        this.reward = reward;
        if (!reward.getOptions().contains(this))
            reward.getOptions().add(this);
    }

    public void addOptionValue(OptionValue optionValue) {
        this.getOptionValues().add(optionValue);
        if (optionValue.getOption() != this)
            optionValue.setOption(this);
    }
}
