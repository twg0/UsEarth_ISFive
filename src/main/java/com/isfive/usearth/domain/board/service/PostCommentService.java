package com.isfive.usearth.domain.board.service;

import com.isfive.usearth.domain.board.Retry;
import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.entity.PostComment;
import com.isfive.usearth.domain.board.repository.PostCommentRepository;
import com.isfive.usearth.domain.board.repository.PostRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostCommentService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostCommentRepository postCommentRepository;

    @Retry
    @Transactional
    public void createComment(Long postId, String content, String email) {
        Post post = postRepository.findByIdWithMember(postId);
        post.verifyNotWriter(email);

        Member member = memberRepository.findByEmailOrThrow(email);

        PostComment postComment = PostComment.createPostComment(member, post, content);
        postCommentRepository.save(postComment);
        post.increaseCommentCount();
    }
}
