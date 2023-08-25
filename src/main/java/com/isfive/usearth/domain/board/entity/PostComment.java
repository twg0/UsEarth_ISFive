package com.isfive.usearth.domain.board.entity;

import com.isfive.usearth.domain.member.entity.Member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private String content;

    @Builder
    private PostComment(Member member, Post post, String content) {
        this.member = member;
        this.post = post;
        this.content = content;
    }

    public static PostComment createPostComment(Member member, Post post, String content) {
        return PostComment.builder()
                .member(member)
                .post(post)
                .content(content)
                .build();
    }
}
