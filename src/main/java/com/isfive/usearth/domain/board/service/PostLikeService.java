package com.isfive.usearth.domain.board.service;

import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.entity.PostLike;
import com.isfive.usearth.domain.board.repository.PostLikeRepository;
import com.isfive.usearth.domain.board.repository.PostRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostLikeService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public void clickLike(Long postId, String email) {
        Post post = postRepository.findByIdOrThrow(postId);
        post.verifyNotWriter(email);

        Member member = memberRepository.findByEmailOrThrow(email);
        Optional<PostLike> optionalPostLike = postLikeRepository.findByPostAndMember(post, member);

        if (optionalPostLike.isPresent()) {
            cancelLike(post, optionalPostLike.get());
        } else {
            like(post, member);
        }
    }

    public boolean isPostLikedByUser(Long postId, String email) {
        return postLikeRepository.existsByPost_IdAndMember_Email(postId, email);
    }

    private void cancelLike(Post post, PostLike postLike) {
        postLikeRepository.delete(postLike);
        post.cancelLike();
    }

    private void like(Post post, Member member) {
        PostLike postLike = new PostLike(member, post);
        postLikeRepository.save(postLike);
        post.like();
    }
}
