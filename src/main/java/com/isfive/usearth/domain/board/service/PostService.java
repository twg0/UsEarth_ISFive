package com.isfive.usearth.domain.board.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.isfive.usearth.exception.EntityNotFoundException;
import com.isfive.usearth.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

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

    /**
     * Page<Post>를 조회 한 후 List<PostsResponse> 로 만든다.
     * 로그인 한 사용자가 좋아요를 누른 Post에 일치하는 PostsResponse에는
     * 좋아요 표시를 할 수 있도록 true 값을 설정해주고 Page<> 타입으로 변환 후 반환한다.
     */
    public Page<PostsResponse> readPosts(Long boardId, Integer page, String email) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Post> posts = postRepository.findPosts(boardId, pageRequest);
        List<PostsResponse> postsResponses = createPostResponses(posts);

        List<PostLike> postLikes = postLikeRepository.findByMember_EmailAndPostIn(email, posts.getContent());

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
        Post post = postRepository.findByIdWithMember(postId);
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
        post.increaseLikeCount();
    }
}
