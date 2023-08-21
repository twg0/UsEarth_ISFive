package com.isfive.usearth.domain.project.dto;

import java.util.List;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.maker.entity.Maker;
import com.isfive.usearth.domain.project.entity.Project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRegisterDto {
    private String title;
    private String summary;
    private String story;
    private Integer targetAmount;
    private Period fundingDate;
    private List<String> tagList;
    private Maker maker;
    private FileImage repImage;

    public Project toEntity() {
        return Project.builder()
                .title(title)
                .summary(summary)
                .story(story)
                .targetAmount(targetAmount)
                .repImage(repImage)
                .maker(maker)
                .fundingDate(fundingDate)
                .build();
    }
}
