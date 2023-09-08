package com.isfive.usearth.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isfive.usearth.domain.board.entity.PostFileImage;

import java.util.List;

public interface PostFileImageRepository extends JpaRepository<PostFileImage, Long> {

    List<PostFileImage> findAllByPost_Id(Long postId);
}
