package com.isfive.usearth.domain.board.service;

import com.isfive.usearth.domain.board.annotation.Retry;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostCommentService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostCommentRepository postCommentRepository;

    @Retry
    @Transactional
    public void createComment(Long postId, String content, String username) {
        Post post = postRepository.findByIdWithMember(postId);
        post.verifyNotWriter(username);

        Member member = memberRepository.findByUsernameOrThrow(username);

        PostComment postComment = PostComment.createPostComment(member, post, content);
        postCommentRepository.save(postComment);
        post.increaseCommentCount();
    }

    @Retry
    @Transactional
    public void createReply(Long commentId, String content, String username) {
        PostComment postComment = postCommentRepository.findByIdWithPost(commentId).orElseThrow();

        Member member = memberRepository.findByUsernameOrThrow(username);
        PostComment reply = PostComment.createPostComment(member, postComment.getPost(), content);
        postComment.addReply(reply);
        postCommentRepository.save(reply);
    }

    @Transactional
    public void deleteComment(Long commentId, String username) {
        PostComment postComment = postCommentRepository.findByIdWithMember(commentId).orElseThrow();
        postComment.verifyWriter(username);
        postComment.delete();
    }
}
