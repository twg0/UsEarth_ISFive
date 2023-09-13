package com.isfive.usearth.domain.maker.repository;

import com.isfive.usearth.domain.maker.entity.Maker;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakerRepository extends JpaRepository<Maker, Long> {

	default Maker findByIdOrThrow(Long makerId) {
		return findById(makerId)
			.orElseThrow(() -> new EntityNotFoundException());
	}

	Maker findByName(String name);
}
