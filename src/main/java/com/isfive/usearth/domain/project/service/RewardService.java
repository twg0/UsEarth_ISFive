package com.isfive.usearth.domain.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isfive.usearth.domain.project.dto.RewardCreate;
import com.isfive.usearth.domain.project.entity.Option;
import com.isfive.usearth.domain.project.entity.Reward;
import com.isfive.usearth.domain.project.entity.RewardSku;
import com.isfive.usearth.domain.project.repository.RewardRepository;

import lombok.RequiredArgsConstructor;

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

            List<RewardSku> rewardSkus = optionService.createRewardSku(rewardCreate.getOptionStocks(), reward);
            for (RewardSku rewardSku : rewardSkus)
                reward.addRewardSku(rewardSku);

            rewards.add(reward);
        }
        rewardRepository.saveAll(rewards);
        return rewards;
    }
}
