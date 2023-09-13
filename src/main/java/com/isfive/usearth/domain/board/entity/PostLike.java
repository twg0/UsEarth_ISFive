package com.isfive.usearth.domain.board.entity;

import com.isfive.usearth.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	public PostLike(Member member, Post post) {
		this.member = member;
		this.post = post;
	}

	public Long getPostId() {
		return post.getId();
	}
}
