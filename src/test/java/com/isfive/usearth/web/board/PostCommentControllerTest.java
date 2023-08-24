package com.isfive.usearth.web.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isfive.usearth.domain.board.entity.Board;
import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.repository.BoardRepository;
import com.isfive.usearth.domain.board.repository.PostRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.web.board.dto.PostCommentCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class PostCommentControllerTest {

    @Autowired MockMvc mockMvc;

    @Autowired ObjectMapper objectMapper;

    @Autowired BoardRepository boardRepository;

    @Autowired PostRepository postRepository;

    @Autowired MemberRepository memberRepository;

    @DisplayName("사용자는 댓글을 작성할 수 있다.")
    @Test
    void writeComment() throws Exception {
        //given
        Member writer = Member.builder()
                .email("post writer")
                .build();

        Member member = Member.builder()
                .email("comment writer")
                .build();

        memberRepository.save(member);

        Board board = Board.createBoard("게시판 제목", "게시판 요약");
        boardRepository.save(board);

        Post post = Post.createPost(member, board, "title", "content");
        postRepository.save(post);
        PostCommentCreateRequest request = new PostCommentCreateRequest("댓글입니다.");
        //when //then

        mockMvc.perform(post("/posts/{postId}/comments", post.getId())
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andDo(MockMvcResultHandlers.print());

    }
}