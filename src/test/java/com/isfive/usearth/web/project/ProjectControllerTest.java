package com.isfive.usearth.web.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.common.Period;
import com.isfive.usearth.domain.maker.entity.Individual;
import com.isfive.usearth.domain.maker.entity.Maker;
import com.isfive.usearth.domain.maker.repository.MakerRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.project.dto.ProjectCreate;
import com.isfive.usearth.domain.project.dto.RewardCreate;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.repository.RewardRepository;
import com.isfive.usearth.domain.project.service.ProjectService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.repository.ProjectRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ProjectControllerTest {

	@Autowired MockMvc mockMvc;

	@Autowired ProjectRepository projectRepository;
	@Autowired MemberRepository memberRepository;
	@Autowired MakerRepository makerRepository;
	@Autowired RewardRepository rewardRepository;

	@Autowired ProjectService projectService;

	@Autowired EntityManager em;

	@DisplayName("사용자는 프로젝트 목록을 조회할 수 있다.")
	@Test
	void findProjects() throws Exception {
		//given
		for (int i = 0; i < 20; i++) {
			Project project = Project.builder()
					.title("title" + i)
					.build();

			projectRepository.save(project);
		}

		//when //then
		mockMvc.perform(get("/projects")
						.param("page", "0")
						.param("size", "10"))
				.andExpect(jsonPath("$.content").isArray())
				.andExpect(jsonPath("$.numberOfElements").value("10"))
				.andExpect(jsonPath("$.content[0].title").value("title0"))
				.andExpect(jsonPath("$.content[9].title").value("title9"))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@DisplayName("사용자는 프로젝트를 단건 조회할 수 있다.")
	@Test
	void findProject() throws Exception {
		//given
		Individual individual = Individual.builder().name("개인").build();
		makerRepository.save(individual);

		Project project = Project.builder().maker(individual).title("프로젝트").build();
		Reward reward1 = Reward.builder().project(project).title("리워드1").build();
		Reward reward2 = Reward.builder().project(project).title("리워드2").build();
		projectRepository.save(project);
		rewardRepository.save(reward1);
		rewardRepository.save(reward2);
		em.flush();
		em.clear();

		//when //then

		mockMvc.perform(get("/projects/{projectId}", project.getId())
						.contentType(APPLICATION_JSON))
				.andExpect(jsonPath("title").value("프로젝트"))
				.andExpect(jsonPath("rewardsResponses.size()").value(2))
				.andExpect(jsonPath("rewardsResponses[0].title").value("리워드1"))
				.andExpect(jsonPath("rewardsResponses[1].title").value("리워드2"))
				.andExpect(status().isOk())
				.andDo(print());
	}
}