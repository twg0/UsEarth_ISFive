package com.isfive.usearth.domain.board.service;

import com.isfive.usearth.domain.board.dto.PostResponse;
import com.isfive.usearth.domain.board.entity.Board;
import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.repository.BoardRepository;
import com.isfive.usearth.domain.board.repository.PostRepository;
import com.isfive.usearth.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createPost(Long boardId, String username, String title, String content) {
        //TODO 추후 수정
        Member fakeMember = new Member();
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);
        Post post = Post.createPost(fakeMember, board, title, content);
        postRepository.save(post);
    }

    public Page<PostResponse> readPosts(Long boardId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);
        Page<Post> posts = postRepository.findAllByBoard_IdOrderByIdDesc(boardId, pageRequest);
        List<PostResponse> postResponses = createPostResponses(posts);
        return new PageImpl<>(postResponses, pageRequest, posts.getTotalElements());
    }

    private List<PostResponse> createPostResponses(Page<Post> posts) {
        return posts.stream()
                .map(PostResponse::new)
                .toList();
    }
}
