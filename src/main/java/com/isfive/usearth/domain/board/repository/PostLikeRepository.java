package com.isfive.usearth.domain.board.repository;

import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.entity.PostLike;
import com.isfive.usearth.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByPostAndMember(Post post, Member member);
}
