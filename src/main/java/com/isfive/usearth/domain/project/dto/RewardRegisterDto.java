package com.isfive.usearth.domain.project.dto;

import com.isfive.usearth.domain.project.entity.Option;
import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.entity.Reward;

import java.util.List;

public class RewardRegisterDto {
    private Project project;
    private String title;
    private String description;
    private Integer price;
    private Integer initStock;
    private String expectedSendDate;
    private Integer deliveryFee;
    private List<Option> optionList;

    public Reward toEntity() {
        return Reward.builder()
                .project(project)
                .title(title)
                .description(description)
                .price(price)
                .initStock(initStock)
                .expectedSendDate(expectedSendDate)
                .deliveryFee(deliveryFee)
                .options(optionList)
                .build();
    }

}
