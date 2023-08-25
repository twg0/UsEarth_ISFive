package com.isfive.usearth.domain.board.service;

import com.isfive.usearth.domain.board.entity.Board;
import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.repository.BoardRepository;
import com.isfive.usearth.domain.board.repository.PostLikeRepository;
import com.isfive.usearth.domain.board.repository.PostRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.isfive.usearth.domain.board.entity.Post.createPost;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PostCommentServiceTest {

    @Autowired MemberRepository memberRepository;

    @Autowired BoardRepository boardRepository;

    @Autowired PostRepository postRepository;

    @Autowired PostLikeRepository postLikeRepository;

    @Autowired PostCommentService postCommentService;

    @DisplayName("댓글 작성 동시성 문제 발생 시 재시도한다.(로그 확인용)")
    @Test
    void retryLogCheck() throws InterruptedException {

        int count = 5;
        Member writer = Member.builder().email("writer").build();
        memberRepository.save(writer);
        for (int i = 0; i < count; i++) {
            Member member = Member.builder().email("member" + i).build();
            memberRepository.save(member);
        }

        Board board = Board.createBoard("게시판 제목", "게시판 요약");
        boardRepository.save(board);

        Post post = createPost(writer, board, "제목1", "내용1");
        postRepository.save(post);

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(count);

        // when

        for (int i = 0; i < count; i++) {
            String email = "member" + i;
            String content = "content" + i;
            executorService.submit(() -> {
                        try {
                            postCommentService.createComment(post.getId(), content, email);
                        }
                        finally {
                            latch.countDown();
                        }
                    }
            );
        }

        latch.await();


    }
}