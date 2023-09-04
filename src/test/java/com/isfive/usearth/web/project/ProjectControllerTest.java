package com.isfive.usearth.web.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isfive.usearth.domain.common.FileImageService;
import com.isfive.usearth.domain.maker.entity.Individual;
import com.isfive.usearth.domain.maker.repository.MakerRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.project.entity.Project;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.repository.ProjectRepository;
import com.isfive.usearth.domain.project.repository.RewardRepository;
import com.isfive.usearth.domain.project.service.ProjectService;
import com.isfive.usearth.web.project.dto.ProjectRegister;
import com.isfive.usearth.web.project.dto.RewardRegister;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class ProjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MakerRepository makerRepository;
    @Autowired
    RewardRepository rewardRepository;

    @Autowired
    ProjectService projectService;

    @MockBean
    private FileImageService fileService;

    @Autowired
    EntityManager em;

    @WithMockUser(username = "member")
    @DisplayName("사용자는 프로젝트를 생성할 수 있다.")
    @Test
    void createProject() throws Exception {
        // given
        Member member = Member.builder()
                .username("member")
                .build();

        memberRepository.save(member);

        Individual individual = Individual.builder().name("abc마트").build();
        individual.setMember(member);
        makerRepository.save(individual);

        ProjectRegister projectRegister = ProjectRegister.builder()
                .title("신발")
                .summary("본인에게 맞는 신발을 찾아보세요.")
                .story("슬리퍼, 구두, 운동화 등 다양한 종류와 옵션들을 준비했습니다.")
                .targetAmount(1000000)
                .startDate("2023-08-30")
                .endDate("2023-09-30")
                .makerName("abc마트")
                .hashTag("#신발 #슬리퍼 #구두 #운동화")
                .build();

        MockMultipartFile repImage = new MockMultipartFile(
                "repImage", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "ImageData".getBytes()
        );

        // MockMultipartFile for project image
        MockMultipartFile projectImageFile1 = new MockMultipartFile(
                "projectImageList", "project_image1.jpg", MediaType.IMAGE_JPEG_VALUE, "ImageData1".getBytes()
        );
        MockMultipartFile projectImageFile2 = new MockMultipartFile(
                "projectImageList", "project_image2.jpg", MediaType.IMAGE_JPEG_VALUE, "ImageData2".getBytes()
        );
        MockMultipartFile projectImageFile3 = new MockMultipartFile(
                "projectImageList", "project_image3.jpg", MediaType.IMAGE_JPEG_VALUE, "ImageData3".getBytes()
        );

        List<MockMultipartFile> projectImageFileList = new ArrayList<>();
        projectImageFileList.add(projectImageFile1);
        projectImageFileList.add(projectImageFile2);
        projectImageFileList.add(projectImageFile3);

        Mockito.when(fileService.createFileImage(Mockito.any(MultipartFile.class))).thenReturn(null);

        Map<String, String> options1 = new HashMap<>();
        options1.put("색상", "블랙, 화이트");
        options1.put("사이즈", "240, 250, 260, 270, 280");

        Map<String, Integer> optionStocks1 = new HashMap<>();
        optionStocks1.put("블랙, 240", 10);
        optionStocks1.put("블랙, 250", 10);
        optionStocks1.put("블랙, 260", 10);
        optionStocks1.put("블랙, 270", 10);
        optionStocks1.put("블랙, 280", 10);
        optionStocks1.put("화이트, 240", 15);
        optionStocks1.put("화이트, 250", 15);
        optionStocks1.put("화이트, 260", 15);
        optionStocks1.put("화이트, 270", 15);
        optionStocks1.put("화이트, 280", 15);

        RewardRegister rewardRegister1 = RewardRegister.builder()
                .title("슬리퍼")
                .description("슬리퍼입니다.")
                .price(30000)
                .expectedSendDate("2023-10-10")
                .deliveryFee(3000)
                .options(options1)
                .optionStocks(optionStocks1)
                .build();

        Map<String, String> options2 = new HashMap<>();
        options2.put("색상", "그린, 옐로우");
        options2.put("사이즈", "240, 250, 260, 270, 280");

        Map<String, Integer> optionStocks2 = new HashMap<>();
        optionStocks2.put("그린, 240", 20);
        optionStocks2.put("그린, 250", 20);
        optionStocks2.put("그린, 260", 20);
        optionStocks2.put("그린, 270", 20);
        optionStocks2.put("그린, 280", 20);
        optionStocks2.put("옐로우, 240", 25);
        optionStocks2.put("옐로우, 250", 25);
        optionStocks2.put("옐로우, 260", 25);
        optionStocks2.put("옐로우, 270", 25);
        optionStocks2.put("옐로우, 280", 25);

        RewardRegister rewardRegister2 = RewardRegister.builder()
                .title("운동화")
                .description("운동입니다.")
                .price(30000)
                .expectedSendDate("2023-10-10")
                .deliveryFee(3000)
                .options(options2)
                .optionStocks(optionStocks2)
                .build();

        List<RewardRegister> rewardRegisterList = new ArrayList<>();
        rewardRegisterList.add(rewardRegister1);
        rewardRegisterList.add(rewardRegister2);

		MockMultipartFile projectDTO = new MockMultipartFile("projectRegister", "projectRegister",
				"application/json",
				objectMapper.writeValueAsString(projectRegister).getBytes(StandardCharsets.UTF_8));

		MockMultipartFile rewardDTO = new MockMultipartFile("rewardRegisterList", "rewardRegisterList",
				"application/json",
				objectMapper.writeValueAsString(rewardRegisterList).getBytes(StandardCharsets.UTF_8));

        // when // then
		mockMvc.perform(MockMvcRequestBuilders.multipart("/projects")
                        .file(new MockMultipartFile("repImage", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "ImageData".getBytes()))
                        .file(new MockMultipartFile("projectImageList", "project_image1.jpg", MediaType.IMAGE_JPEG_VALUE, "ImageData1".getBytes()))
                        .file(new MockMultipartFile("projectImageList", "project_image2.jpg", MediaType.IMAGE_JPEG_VALUE, "ImageData2".getBytes()))
                        .file(projectDTO)
                        .file(rewardDTO)
						.contentType(MediaType.MULTIPART_FORM_DATA)
				)
				.andDo(print())
				.andExpect(status().isCreated());
    }

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