package com.isfive.usearth.domain.project.repository;

import com.isfive.usearth.domain.project.entity.RewardSku;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.project.entity.SkuValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SkuValueRepository extends JpaRepository<SkuValue, Long> {
}
