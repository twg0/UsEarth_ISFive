package com.isfive.usearth.web.project;

import com.isfive.usearth.domain.common.Result;
import com.isfive.usearth.domain.project.dto.RewardsResponse;
import com.isfive.usearth.domain.project.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    @GetMapping("/projects/{projectId}/rewards")
    public ResponseEntity<Result<RewardsResponse>> findRewards(@PathVariable Long projectId) {
        return new ResponseEntity<>(rewardService.findRewards(projectId), HttpStatus.OK);
    }
}
