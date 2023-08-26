package com.isfive.usearth.web.board;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.isfive.usearth.domain.board.entity.PostComment;
import com.isfive.usearth.domain.board.entity.PostLike;
import com.isfive.usearth.domain.board.repository.PostCommentRepository;
import com.isfive.usearth.domain.board.repository.PostLikeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isfive.usearth.domain.board.entity.Board;
import com.isfive.usearth.domain.board.entity.Post;
import com.isfive.usearth.domain.board.repository.BoardRepository;
import com.isfive.usearth.domain.board.repository.post.PostRepository;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.web.board.dto.PostCreateRequest;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PostControllerTest {

	@Autowired MockMvc mockMvc;

	@Autowired ObjectMapper objectMapper;

	@Autowired BoardRepository boardRepository;

	@Autowired PostRepository postRepository;

	@Autowired PostLikeRepository postLikeRepository;

	@Autowired MemberRepository memberRepository;

	@Autowired PostCommentRepository postCommentRepository;

	@WithMockUser(username = "writer")
	@DisplayName("사용자는 게시글을 작성 할 수 있다.")
	@Test
	void writePost() throws Exception {
		//given
		Member member = Member.builder()
			.username("writer")
			.build();

		memberRepository.save(member);

		Board board = Board.createBoard("게시판 제목", "게시판 요약");
		boardRepository.save(board);

		PostCreateRequest request = PostCreateRequest.builder()
			.title("제목")
			.content("내용")
			.build();

		//when   //then
		mockMvc.perform(post("/boards/{boardId}/posts", board.getId())
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))
			)
			.andExpect(status().isOk())
			.andDo(print());

		List<Post> all = postRepository.findAll();
		assertThat(all.size()).isEqualTo(1);
	}

	@WithMockUser(username = "member")
	@DisplayName("사용자는 게시글을 페이징 조회 할 수 있다.")
	@Test
	void findPosts() throws Exception {
		//given
		Member member = Member.builder()
			.username("member")
			.build();

		memberRepository.save(member);

		Board board = Board.createBoard("게시판 제목", "게시판 요약");
		boardRepository.save(board);

		for (int i = 1; i <= 20; i++) {
			Post post = Post.createPost(member, board, "title" + i, "content" + i);
			postRepository.save(post);
		}

		//when //then
		mockMvc.perform(get("/boards/{boardId}/posts", board.getId())
				.contentType(APPLICATION_JSON)
				.param("page", "1")
			)
			.andExpect(jsonPath("$.content").isArray())
			.andExpect(jsonPath("$.content.size()").value(10))
			.andExpect(jsonPath("$.content[0].title").value("title20"))
			.andExpect(jsonPath("$.content[9].title").value("title11"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@WithMockUser(username = "member")
	@DisplayName("사용자는 게시글을 조회 할 수 있다.")
	@Test
	void findPost() throws Exception {
		//given
		Member member = Member.builder()
				.username("member")
				.nickname("nickname")
				.build();

		memberRepository.save(member);

		Board board = Board.createBoard("게시판 제목", "게시판 요약");
		boardRepository.save(board);

		Post post = Post.createPost(member, board, "title", "content");
		postRepository.save(post);

		PostComment postComment1 = PostComment.createPostComment(member, post, "댓글1");
		PostComment postComment2 = PostComment.createPostComment(member, post, "댓글2");

		postCommentRepository.save(postComment1);
		postCommentRepository.save(postComment2);

		PostComment reply1 = PostComment.createPostComment(member, post, "답글1.");
		PostComment reply2 = PostComment.createPostComment(member, post, "답글2.");
		PostComment reply3 = PostComment.createPostComment(member, post, "답글3.");
		PostComment reply4 = PostComment.createPostComment(member, post, "답글4.");
		postComment1.addReply(reply1);
		postComment1.addReply(reply2);
		postComment2.addReply(reply3);
		postComment2.addReply(reply4);

		postCommentRepository.save(reply1);
		postCommentRepository.save(reply2);
		postCommentRepository.save(reply3);
		postCommentRepository.save(reply4);

		//when //then
		mockMvc.perform(get("/posts/{postId}", post.getId())
						.contentType(APPLICATION_JSON)
				)
				.andExpect(jsonPath("$.id").value(post.getId()))
				.andExpect(jsonPath("$.title").value(post.getTitle()))
				.andExpect(jsonPath("$.writer").value(member.getNickname()))
				.andExpect(jsonPath("$.content").value(post.getContent()))
				.andExpect(jsonPath("$.views").value(post.getViews()))
				.andExpect(jsonPath("$.postCommentResponse.content.size()").value(2))
				.andExpect(jsonPath("$.postCommentResponse.content[0].postCommentResponses.size()").value(2))
				.andExpect(jsonPath("$.postCommentResponse.content[1].postCommentResponses.size()").value(2))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@WithMockUser(username = "member")
	@DisplayName("사용자는 게시글을 좋아요 할 수 있다.")
	@Test
	void like() throws Exception {
		//given
		Member writer = Member.builder()
				.username("writer")
				.build();

		Member member2 = Member.builder()
				.username("member")
				.build();

		memberRepository.save(writer);
		memberRepository.save(member2);

		Board board = Board.createBoard("게시판 제목", "게시판 요약");
		boardRepository.save(board);

		Post post = Post.createPost(writer, board, "title", "content");
		postRepository.save(post);

		//when //then
		mockMvc.perform(post("/posts/{postId}/like", post.getId()))
				.andExpect(status().isOk())
				.andDo(print());

		List<PostLike> all = postLikeRepository.findAll();
		assertThat(all).isNotEmpty();

		PostLike postLike = all.get(0);
		assertThat(postLike.getPost()).isEqualTo(post);
		assertThat(postLike.getMember()).isEqualTo(member2);
	}

	@WithMockUser(username = "member")
	@DisplayName("사용자는 게시글을 좋아요를 취소 할 수 있다.")
	@Test
	void cancelLike() throws Exception {
		//given
		Member writer = Member.builder()
				.username("writer")
				.build();

		Member member = Member.builder()
				.username("member")
				.build();

		memberRepository.save(writer);
		memberRepository.save(member);

		Board board = Board.createBoard("게시판 제목", "게시판 요약");
		boardRepository.save(board);

		Post post = Post.createPost(writer, board, "title", "content");
		postRepository.save(post);

		PostLike postLike = new PostLike(member, post);
		postLikeRepository.save(postLike);

		//when //then
		mockMvc.perform(post("/posts/{postId}/like", post.getId()))
				.andExpect(status().isOk())
				.andDo(print());

		List<PostLike> all = postLikeRepository.findAll();
		assertThat(all).isEmpty();
	}
}