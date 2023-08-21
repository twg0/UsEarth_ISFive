package com.isfive.usearth.domain.project.service;

import org.springframework.stereotype.Service;

import com.isfive.usearth.domain.project.dto.RewardRegisterDto;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.repository.RewardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardRepository rewardRepository;

    public void createReward(RewardRegisterDto dto) {
        Reward reward = dto.toEntity();
        rewardRepository.save(reward);
    }
}
