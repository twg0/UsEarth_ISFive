package com.isfive.usearth.web.funding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isfive.usearth.domain.funding.entity.Address;
import com.isfive.usearth.domain.funding.entity.Delivery;
import com.isfive.usearth.domain.funding.entity.Funding;
import com.isfive.usearth.domain.funding.entity.FundingRewardSku;
import com.isfive.usearth.domain.funding.repository.FundingRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.entity.RewardSku;
import com.isfive.usearth.domain.project.repository.RewardRepository;
import com.isfive.usearth.domain.project.repository.RewardSkuRepository;
import com.isfive.usearth.web.funding.dto.DeliveryRequest;
import com.isfive.usearth.web.funding.dto.FundingRequest;
import com.isfive.usearth.web.funding.dto.PaymentRequest;
import com.isfive.usearth.web.funding.dto.RewardSkuRequest;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class FundingControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired MemberRepository memberRepository;
    @Autowired RewardRepository rewardRepository;
    @Autowired RewardSkuRepository rewardSkuRepository;
    @Autowired FundingRepository fundingRepository;

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
        PaymentRequest paymentRequest = new PaymentRequest("0000-0000-0000-0000", "2025-05", "991111", "12");
        FundingRequest fundingRequest = new FundingRequest(deliveryRequest, paymentRequest, List.of(rewardSkuRequest1, rewardSkuRequest2));

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.post("/funding")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(fundingRequest))
                )
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        List<Funding> all = fundingRepository.findAll();
        assertThat(all).hasSize(1);
        Funding funding = all.get(0);
        assertThat(funding.getDelivery())
                .extracting("address.detail", "address.zipcode", "name", "phone")
                .contains("서울", "1번지", "name", "010-0000-0000");

        List<FundingRewardSku> fundingRewardSkus = funding.getFundingRewardSkus();
        assertThat(fundingRewardSkus).hasSize(2);
        assertThat(fundingRewardSkus.get(0))
                .extracting("rewardSku", "funding", "count", "price")
                .contains(rewardSku1, funding, 2, 10000);
        assertThat(fundingRewardSkus.get(1))
                .extracting("rewardSku", "funding", "count", "price")
                .contains(rewardSku2, funding, 5, 10000);

        //재고 감소 확인
        RewardSku findRewardSku1 = rewardSkuRepository.findById(rewardSku1.getId()).orElseThrow();
        RewardSku findRewardSku2 = rewardSkuRepository.findById(rewardSku2.getId()).orElseThrow();
        assertThat(findRewardSku1.getStock()).isEqualTo(3);
        assertThat(findRewardSku2.getStock()).isEqualTo(5);
    }
}