package com.isfive.usearth.domain.maker.repository;

import com.isfive.usearth.domain.maker.entity.Maker;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MakerRepository extends JpaRepository<Maker,Long> {

    default Maker findByIdOrThrow(Long makerId) {
        return findById(makerId)
                .orElseThrow(()->new EntityNotFoundException());
    }

//    @Query("SELECT m FROM Maker m JOIN FETCH m. WHERE m.id = :makerId ")
//    Maker findMakerWithCorporate(@Param("makerId") Long makerId);


}
