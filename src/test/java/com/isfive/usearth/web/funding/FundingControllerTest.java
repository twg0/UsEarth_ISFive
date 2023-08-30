package com.isfive.usearth.web.funding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isfive.usearth.domain.funding.entity.Address;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.entity.RewardSku;
import com.isfive.usearth.domain.project.repository.RewardRepository;
import com.isfive.usearth.domain.project.repository.RewardSkuRepository;
import com.isfive.usearth.web.funding.dto.DeliveryRequest;
import com.isfive.usearth.web.funding.dto.FundingRequest;
import com.isfive.usearth.web.funding.dto.RewardSkuRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class FundingControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired MemberRepository memberRepository;
    @Autowired RewardRepository rewardRepository;
    @Autowired RewardSkuRepository rewardSkuRepository;

    @WithMockUser(username = "member")
    @DisplayName("사용자는 펀딩을 할 수있다.")
    @Test
    void funding() throws Exception {
        //given
        memberRepository.save(Member.builder().username("member").build());
        Reward reward = Reward.builder().price(10000).build();
        rewardRepository.save(reward);
        RewardSku rewardSku1 = RewardSku.builder().reward(reward).stock(5).initStock(5).build();
        RewardSku rewardSku2 = RewardSku.builder().reward(reward).stock(10).initStock(10).build();
        rewardSkuRepository.saveAll(List.of(rewardSku1, rewardSku2));

        Address address = new Address("서울", "1번지");
        DeliveryRequest deliveryRequest = new DeliveryRequest("name", "010-0000-0000", address);
        RewardSkuRequest rewardSkuRequest1 = new RewardSkuRequest(rewardSku1.getId(), 2);
        RewardSkuRequest rewardSkuRequest2 = new RewardSkuRequest(rewardSku2.getId(), 5);
        FundingRequest fundingRequest = new FundingRequest(deliveryRequest, List.of(rewardSkuRequest1, rewardSkuRequest2));

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.post("/funding")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fundingRequest))
                )
                .andDo(MockMvcResultHandlers.print());
    }
}