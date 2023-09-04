package com.isfive.usearth.domain.funding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.funding.entity.Funding;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FundingRepository extends JpaRepository<Funding, Long> {

    @Query("select distinct f from Funding f join fetch f.member join fetch f.fundingRewardSkus frs join fetch frs.rewardSku")
    Optional<Funding> findFundingToCancel(Long fundingId);
}
