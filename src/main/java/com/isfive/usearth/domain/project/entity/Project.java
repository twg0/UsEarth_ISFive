package com.isfive.usearth.domain.project.entity;

import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.maker.entity.Maker;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String summary;

    private String story;

    private Integer targetAmount;

    private Integer totalFundingAmount;

    @Embedded
    private FileImage repImage; // 대표이미지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maker_id")
    private Maker maker;

    @Embedded
    private Period fundingDate;

    @OneToMany(mappedBy = "project")
    private List<Tag> searchTags = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<Reward> rewards = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectFileImage> projectImages = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<ProjectLike> likes = new ArrayList<>();

    public void setMaker(Maker maker) {
        this.maker = maker;
        maker.getProjects().add(this);
    }

}
