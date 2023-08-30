package com.isfive.usearth.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.project.entity.Reward;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Long> {

    List<Reward> findByProject_Id(Long projectId);
}
