package com.isfive.usearth.domain.board.entity;

import com.isfive.usearth.domain.common.BaseEntity;
import com.isfive.usearth.domain.member.entity.Member;

import com.isfive.usearth.exception.BusinessException;
import com.isfive.usearth.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 128)
    private String title;

    @Lob
    private String content;

    private Integer views;

    private Integer likeCount;

    @Version
    private Long version;

    @Builder
    private Post(Board board, Member member, String title, String content) {
        this.board = board;
        this.member = member;
        this.title = title;
        this.content = content;
        this.views = 0;
        this.likeCount = 0;
    }

    public static Post createPost(Member member, Board board, String title, String content) {
        return Post.builder()
                .member(member)
                .board(board)
                .title(title)
                .content(content)
                .build();
    }

    public String getWriterNickname() {
        return member.getNickname();
    }

    public void verifyNotWriter(String email) {
        if (member.isEqualsEmail(email)) {
            throw new BusinessException(ErrorCode.POST_WRITER_NOT_ALLOW);
        }
    }

    public void increaseView() {
        views++;
    }

    public void like() {
        likeCount ++;
    }

    public void cancelLike() {
        if (likeCount > 0) {
            likeCount--;
        }
    }
}
