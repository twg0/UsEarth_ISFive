package com.isfive.usearth.domain.activity.entity;

import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.common.Permit;
import com.isfive.usearth.domain.member.entity.Member;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 128)
    private String title;
    private String content;
    private String organization;
    private String place;
    private Integer maxMember;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="startDate",column = @Column(name="applicate_date_start")),
            @AttributeOverride(name="dueDate",column=@Column(name="applicate_date_end"))
    })
    private Period applicateDate;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="startDate",column = @Column(name="activity_date_start")),
            @AttributeOverride(name="dueDate",column=@Column(name="activity_date_end"))
    })
    private Period activityDate;

    @Enumerated(EnumType.STRING)
    private Permit status;

    @OneToMany(mappedBy = "activity")
    private List<ActivityFileImage> activityImages = new ArrayList<>();
    @OneToMany(mappedBy = "activity")
    private List<Member> members = new ArrayList<>();

}
