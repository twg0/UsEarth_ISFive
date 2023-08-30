package com.isfive.usearth.domain.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.project.entity.RewardSku;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RewardSkuRepository extends JpaRepository<RewardSku, Long> {

    @Query("select rs from RewardSku rs join fetch rs.reward r where rs.id in :ids")
    List<RewardSku> findAllByIdWithReward(@Param("ids") Set<Long> ids);
}
