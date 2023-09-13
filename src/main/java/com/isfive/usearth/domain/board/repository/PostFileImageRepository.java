package com.isfive.usearth.domain.board.repository;

import com.isfive.usearth.domain.board.entity.PostFileImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostFileImageRepository extends JpaRepository<PostFileImage, Long> {

    List<PostFileImage> findAllByPost_Id(Long postId);
}
