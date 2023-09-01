package com.isfive.usearth.domain.board.entity;

import java.util.ArrayList;
import java.util.List;

import com.isfive.usearth.domain.common.BaseEntity;
import com.isfive.usearth.domain.common.FileImage;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.exception.BusinessException;
import com.isfive.usearth.exception.ErrorCode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostFileImage> postFileImages = new ArrayList<>();

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Integer views;

    @Column(nullable = false)
    private Integer likeCount;

    @Column(nullable = false)
    private Integer commentCount;

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
        this.commentCount = 0;
    }

    public static Post createPost(Member member, Board board, String title, String content) {
        return Post.builder()
                .member(member)
                .board(board)
                .title(title)
                .content(content)
                .build();
    }

    public void addImage(PostFileImage postFileImage) {
        postFileImages.add(postFileImage);
        postFileImage.setPost(this);
    }

    public String getWriterNickname() {
        return member.getNickname();
    }

    public List<FileImage> getFileImages() {
        return postFileImages.stream()
                .map(PostFileImage::getFileImage)
                .toList();
    }

    public void verifyNotWriter(String username) {
        if (member.isEqualsUsername(username)) {
            throw new BusinessException(ErrorCode.POST_WRITER_NOT_ALLOW);
        }
    }

    public void increaseView() {
        views++;
    }

    public void increaseLikeCount() {
        likeCount ++;
    }

    public void increaseCommentCount() {
        commentCount ++;
    }

    public void cancelLike() {
        if (likeCount > 0) {
            likeCount--;
        }
    }
}
