package com.isfive.usearth.domain.project.service;

import com.isfive.usearth.domain.project.dto.RewardCreate;
import com.isfive.usearth.domain.project.entity.Option;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.repository.RewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RewardService {

    private final RewardRepository rewardRepository;
    private final OptionService optionService;

    @Transactional
    public List<Reward> createReward(List<RewardCreate> rewardCreateList) {
        List<Reward> rewards = new ArrayList<>();
        for (RewardCreate rewardCreate : rewardCreateList) {
            Reward reward = rewardCreate.toEntity();
            List<Option> options = optionService.convertOption(rewardCreate.getOptions(), reward);
            for (Option option : options)
                reward.addOption(option);
            rewards.add(reward);
        }
        rewardRepository.saveAll(rewards);
        return rewards;
    }
}
