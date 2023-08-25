package com.isfive.usearth.web.project.dto;

import com.isfive.usearth.domain.project.dto.ProjectUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectModify {
    private String title;
    private String summary;

    public ProjectUpdate toService(MultipartFile file) {
        return ProjectUpdate.builder()
                .title(title)
                .summary(summary)
                .repImage(file)
                .build();
    }
}
