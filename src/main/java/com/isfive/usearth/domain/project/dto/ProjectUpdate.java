package com.isfive.usearth.domain.project.dto;

import com.isfive.usearth.domain.common.FileImage;
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
public class ProjectUpdate {
    private String title;
    private String summary;
    private FileImage repImage;

    public Project toEntity() {
        return Project.builder()
                .title(title)
                .summary(summary)
                .build();
    }
}
