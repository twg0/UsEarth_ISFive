package com.isfive.usearth.domain.board.repository;

import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.entity.PostLike;
import com.isfive.usearth.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    List<PostLike> findByMember_EmailAndPostIn(String email, List<Post> posts);

    Optional<PostLike> findByPostAndMember(Post post, Member member);

    boolean existsByPost_IdAndMember_Email(Long postId, String email);
}
