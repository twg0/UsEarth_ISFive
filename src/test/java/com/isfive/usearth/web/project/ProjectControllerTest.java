package com.isfive.usearth.web.project;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.repository.ProjectRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ProjectControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ProjectRepository projectRepository;

	@WithMockUser(username = "member")
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
}