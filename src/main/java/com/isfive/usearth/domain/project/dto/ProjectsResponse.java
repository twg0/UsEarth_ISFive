package com.isfive.usearth.domain.project.dto;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.project.entity.Project;

import lombok.Getter;

@Getter
public class ProjectsResponse {

    private final String title;
    private final FileImage fileImage;
    private final Period fundingDate;
    private final Integer targetAmount;
    private final Integer totalFundingAmount;

    public ProjectsResponse(Project project) {
        this.title = project.getTitle();
        this.fileImage = project.getRepImage();
        this.fundingDate = project.getFundingDate();
        this.targetAmount = project.getTargetAmount();
        this.totalFundingAmount = project.getTotalFundingAmount();
    }
}
