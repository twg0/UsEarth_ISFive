package com.isfive.usearth.domain.board.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isfive.usearth.domain.board.dto.PostsResponse;
import com.isfive.usearth.domain.board.entity.Board;
import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.repository.BoardRepository;
import com.isfive.usearth.domain.board.repository.PostRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createPost(Long boardId, String email, String title, String content) {
        Member member = memberRepository.findByEmailOrThrow(email);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(NoSuchElementException::new);
        Post post = Post.createPost(member, board, title, content);
        postRepository.save(post);
    }

    public Page<PostsResponse> readPosts(Long boardId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Post> posts = postRepository.findAllByBoard_IdOrderByIdDesc(boardId, pageRequest);
        List<PostsResponse> postsResponses = createPostResponses(posts);
        return new PageImpl<>(postsResponses, pageRequest, posts.getTotalElements());
    }

    public void readPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(NoSuchElementException::new);
    }

    private List<PostsResponse> createPostResponses(Page<Post> posts) {
        return posts.stream()
                .map(PostsResponse::new)
                .toList();
    }
}
