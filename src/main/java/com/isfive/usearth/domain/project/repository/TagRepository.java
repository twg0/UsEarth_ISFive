package com.isfive.usearth.domain.project.repository;

import com.isfive.usearth.domain.project.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
