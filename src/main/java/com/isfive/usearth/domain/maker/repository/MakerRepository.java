package com.isfive.usearth.domain.maker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.isfive.usearth.domain.maker.entity.Maker;

import jakarta.persistence.EntityNotFoundException;

public interface MakerRepository extends JpaRepository<Maker,Long> {

    default Maker findByIdOrThrow(Long makerId) {
        return findById(makerId)
                .orElseThrow(()->new EntityNotFoundException());
    }

    @Query("SELECT c FROM CorporateBusiness c WHERE c.id = :makerId ")
    Maker findMakerWithCorporateBusiness(@Param("makerId") Long makerId);

    @Query("SELECT d FROM Individual d WHERE d.id = :makerId ")
    Maker findMakerWithIndividual(@Param("makerId") Long makerId);

    @Query("SELECT p FROM PersonalBusiness p WHERE p.id = :makerId ")
    Maker findMakerWithPersonalBusiness(@Param("makerId") Long makerId);


}
