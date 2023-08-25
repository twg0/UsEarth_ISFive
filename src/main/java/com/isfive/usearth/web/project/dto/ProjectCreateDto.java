package com.isfive.usearth.web.project.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.project.dto.ProjectRegisterDto;
import com.isfive.usearth.domain.project.service.TagService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProjectCreateDto {

    private final TagService tagService;
    private final FileImageService fileImageService;
//    private final MakerRepository makerRepository;

    private String title;
    private String summary;
    private String story;
    private Integer targetAmount;
    private Period fundingDate;
    private String makerName;
    private String hashTag;

    public ProjectRegisterDto toService(MultipartFile file) {
        List<String> tags = tagService.createTagList(hashTag);
        FileImage fileImage = fileImageService.createFileImage(file);

        return ProjectRegisterDto.builder()
                .title(title)
                .summary(summary)
                .story(story)
                .targetAmount(targetAmount)
                .fundingDate(fundingDate)
                .tagList(tags)
                .repImage(fileImage)
                .build();
    }
}
