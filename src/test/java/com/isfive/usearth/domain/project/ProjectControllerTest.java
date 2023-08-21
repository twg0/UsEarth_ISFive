package com.isfive.usearth.domain.project;

import com.isfive.usearth.domain.project.entity.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProjectControllerTest {

    @Autowired MockMvc mockMvc;

    @Autowired ProjectRepository projectRepository;

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