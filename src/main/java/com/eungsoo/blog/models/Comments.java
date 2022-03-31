package com.eungsoo.blog.models;


import com.eungsoo.blog.security.UserDetailsImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Comments extends Timestamped {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

//    @Columning name;(nullable = false)
//    private Str

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private Long contentsId;

    @Column(nullable = false)
    private Long userId;

     public Comments(CommentRequestDto name, UserDetailsImpl comments) {
        this.name = comments.getUser().getUsername();
        this.comments = name.getComments();
        this.userId = comments.getUser().getId();
        this.contentsId = name.getContentsId();
    }

    public Comments(String comments, Long contentsId, Long userId) {
        this.comments = comments;
        this.contentsId = contentsId;
        this.userId = userId;
    }

    // 댓글 생성
    public Comments(CommentRequestDto requestDto) {
//        this.name = requestDto.getName();
        this.comments = requestDto.getComments();
        this.contentsId = requestDto.getContentsId();
        this.userId = userId;
    }

    // 댓글 수정
    public void update(CommentRequestDto requestDto) {
//        this.name = requestDto.getName();
        this.comments = requestDto.getComments();
        this.contentsId = requestDto.getContentsId();
        this.userId = userId;
    }
}