package com.isfive.usearth.domain.funding.repository;

import com.isfive.usearth.domain.funding.entity.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<Funding, Long> {
}
