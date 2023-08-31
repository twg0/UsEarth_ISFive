package com.isfive.usearth.web.project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.isfive.usearth.domain.common.Result;
import com.isfive.usearth.domain.project.dto.RewardsResponse;
import com.isfive.usearth.domain.project.service.RewardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @GetMapping("/projects/{projectId}/rewards")
    public Result<RewardsResponse> findRewards(@PathVariable Long projectId) {
        return rewardService.findRewards(projectId);
    }
}
