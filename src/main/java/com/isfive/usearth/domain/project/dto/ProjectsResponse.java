package com.isfive.usearth.domain.project.dto;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.project.entity.Project;

import lombok.Data;
import lombok.Getter;

@Getter
public class ProjectsResponse {

    private String title;
    private FileImage fileImage;
    private Period fundingDate;
    private Integer targetAmount;
    private Integer totalFundingAmount;

    public ProjectsResponse(Project project) {
        this.title = project.getTitle();
        this.fileImage = project.getRepImage();
        this.fundingDate = project.getFundingDate();
        this.targetAmount = project.getTargetAmount();
        this.totalFundingAmount = project.getTotalFundingAmount();
    }
}
