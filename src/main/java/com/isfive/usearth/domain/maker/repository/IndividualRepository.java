package com.isfive.usearth.domain.maker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.maker.entity.Individual;

public interface IndividualRepository extends JpaRepository<Individual,Long> {
}
