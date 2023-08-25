package com.isfive.usearth.web.project.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.project.dto.ProjectCreate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRegister {

    private String title;
    private String summary;
    private String story;
    private Integer targetAmount;
    private String startDate;
    private String endDate;
    private String makerName;
    private String hashTag;

    public ProjectCreate toService(MultipartFile file) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Period period = Period.builder()
                .startDate(LocalDate.parse(startDate, formatter))
                .dueDate(LocalDate.parse(endDate, formatter))
                .build();

        return ProjectCreate.builder()
                .title(title)
                .summary(summary)
                .story(story)
                .targetAmount(targetAmount)
                .fundingDate(period)
                .makerName(makerName)
                .hashTag(hashTag)
                .repImage(file)
                .build();
    }
}
