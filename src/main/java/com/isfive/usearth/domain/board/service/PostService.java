package com.isfive.usearth.domain.board.service;

import com.isfive.usearth.domain.board.dto.PostResponse;
import com.isfive.usearth.domain.board.dto.PostsResponse;
import com.isfive.usearth.domain.board.entity.Board;
import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.entity.PostLike;
import com.isfive.usearth.domain.board.repository.BoardRepository;
import com.isfive.usearth.domain.board.repository.PostLikeRepository;
import com.isfive.usearth.domain.board.repository.PostRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    public void createPost(Long boardId, String email, String title, String content) {
        Member member = memberRepository.findByEmailOrThrow(email);
        Board board = boardRepository.findByIdOrThrow(boardId);
        Post post = Post.createPost(member, board, title, content);
        postRepository.save(post);
    }

    public Page<PostsResponse> readPosts(Long boardId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Post> posts = postRepository.findAllByBoard_IdOrderByIdDesc(boardId, pageRequest);
        List<PostsResponse> postsResponses = createPostResponses(posts);

        // TODO 이메일 추후 수정
        List<PostLike> postLikes = postLikeRepository.findByMember_EmailAndPostIn("other", posts.getContent());

        Set<Long> postIdSet = createPostIdSetBy(postLikes);
        setLikedByUser(postsResponses, postIdSet);

        return new PageImpl<>(postsResponses, pageRequest, posts.getTotalElements());
    }

    @Transactional
    public PostResponse readPost(Long postId) {
        Post post = postRepository.findByIdOrThrow(postId);
        post.increaseView();

        PostResponse postResponse = new PostResponse(post);

        // TODO 이메일 파라미터 추후 수정
        boolean likedByUser = postLikeRepository.existsByPost_IdAndMember_Email(postId, null);
        postResponse.setLikedByUser(likedByUser);

        return postResponse;
    }

    @Transactional
    public void like(Long postId, String email) {
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

    private List<PostsResponse> createPostResponses(Page<Post> posts) {
        return posts.stream()
                .map(PostsResponse::new)
                .toList();
    }


    private  Set<Long> createPostIdSetBy(List<PostLike> postLikes) {
        Set<Long> postIdSet = new HashSet<>();
        postLikes.forEach(postLike ->
                postIdSet.add(postLike.getPostId()));
        return postIdSet;
    }

    private void setLikedByUser(List<PostsResponse> postsResponses, Set<Long> postIdSet) {
        postsResponses.forEach(postsResponse -> {
            if (postIdSet.contains(postsResponse.getId())) {
                postsResponse.setLikedByUser(true);
            }
        });
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
