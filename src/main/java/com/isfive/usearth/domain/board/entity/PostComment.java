package com.isfive.usearth.domain.board.entity;

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
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_comment_id")
    private PostComment postComment;

    @OneToMany(mappedBy = "postComment")
    private List<PostComment> postComments = new ArrayList<>();

    private String content;

    private boolean delete;

    @Builder
    private PostComment(Member member, Post post, String content, boolean delete) {
        this.member = member;
        this.post = post;
        this.content = content;
        this.delete = delete;
    }

    public static PostComment createPostComment(Member member, Post post, String content) {
        return PostComment.builder()
                .member(member)
                .post(post)
                .content(content)
                .delete(false)
                .build();
    }

    public void addReply(PostComment reply) {
        if (getPostComment() != null) {
            throw new RuntimeException("대댓글에는 대댓글을 작성 할 수 없습니다.");
        }
        if (delete) {
            throw new RuntimeException("삭제된 댓글에는 댓글을 작성 할 수 없습니다.");
        }
        postComments.add(reply);
        reply.setPostComment(this);
        post.increaseCommentCount();
    }

    public void setPostComment(PostComment postComment) {
        this.postComment = postComment;
    }
}
