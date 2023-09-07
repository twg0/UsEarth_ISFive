package com.isfive.usearth.web.project.dto;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.project.dto.ProjectCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRegister {

	@NotBlank(message = "프로젝트 제목을 입력해야 합니다.")
	private String title;

	@NotBlank(message = "프로젝트 요약을 입력해야 합니다.")
	private String summary;

	@NotBlank(message = "프로젝트 내용을 입력해야 합니다.")
	private String story;

	@NotNull(message = "프로젝트 목표금액을 입력해야 합니다.")
	private Integer targetAmount;

	@NotBlank(message = "프로젝트 시작 날짜를 입력해야 합니다.")
	private String startDate;

	@NotBlank(message = "프로젝트 종료 날짜를 입력해야 합니다.")
	private String endDate;

	@NotBlank(message = "메이커 이름을 입력해야 합니다. 메이커는 이미 등록된 메이커만 가능합니다.")
	private String makerName;

	@NotBlank(message = "프로젝트 해시태그를 입력해야 합니다. 예시) #상의 #하의 #반팔")
	private String hashTag;

	public ProjectCreate toService(FileImage file) {
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
