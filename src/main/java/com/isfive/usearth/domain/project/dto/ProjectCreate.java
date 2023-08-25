package com.isfive.usearth.domain.project.dto;

import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.project.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCreate {
    private String title;
    private String summary;
    private String story;
    private Integer targetAmount;
    private Period fundingDate;
    private String hashTag;
    private String makerName;
    private MultipartFile repImage;

    public Project toEntity() {
        return Project.builder()
                .title(title)
                .summary(summary)
                .story(story)
                .targetAmount(targetAmount)
                .fundingDate(fundingDate)
                .build();
    }
}
