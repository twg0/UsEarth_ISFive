package com.isfive.usearth.domain.funding.repository;

import com.isfive.usearth.domain.funding.entity.Funding;
import com.isfive.usearth.domain.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long> {
    List<Funding> findByFundingRewardSkus_RewardSku_Reward_Project(Project project);
}
