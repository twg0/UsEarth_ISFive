package com.isfive.usearth.domain.board.entity;

import com.isfive.usearth.domain.common.BaseEntity;
import com.isfive.usearth.domain.member.entity.Member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    //TODO 연관관계 설정
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    @Column(length = 128)
    private String title;

    @Lob
    private String content;

    private Integer views;

    @Builder
    private Post(Board board, Member member, String title, String content) {
        this.board = board;
//        this.member = member;
        this.title = title;
        this.content = content;
        this.views = 0;
    }

    public static Post createPost(Member member, Board board, String title, String content) {
        return Post.builder()
                .member(member)
                .board(board)
                .title(title)
                .content(content)
                .build();
    }
}
