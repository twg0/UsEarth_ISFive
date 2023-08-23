package com.isfive.usearth.domain.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SkuValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer initStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_value_id")
    private OptionValue optionValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reward_sku_id")
    private RewardSku rewardSku;

    public void setOption(Option option) {
        this.option = option;
        option.getSkuValues().add(this);
    }

    public void setOptionValue(OptionValue optionValue) {
        this.optionValue = optionValue;
        optionValue.getSkuValues().add(this);
    }
}
