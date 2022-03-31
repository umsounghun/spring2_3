package com.eungsoo.blog.controller;


import com.eungsoo.blog.models.CommentRequestDto;
import com.eungsoo.blog.models.Comments;
import com.eungsoo.blog.models.Contents;
import com.eungsoo.blog.models.ContentsRequestDto;
import com.eungsoo.blog.repository.CommentsRepository;
import com.eungsoo.blog.repository.ContentsRepository;
import com.eungsoo.blog.security.UserDetailsImpl;
import com.eungsoo.blog.service.CommentsService;
import com.eungsoo.blog.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentsRestController {

    private final com.eungsoo.blog.repository.CommentsRepository CommentsRepository;
    private final com.eungsoo.blog.service.CommentsService CommentsService;

//    // 게시글 전체 조회
//    @GetMapping("/api/comments")
//    public List<Comments> getComments() {
//        return CommentsRepository.findAllByOrderByCreatedAtDesc();
//    }

    // 댓글 특정 조회
    @GetMapping("/api/comments/{idx}")
    // Long id = 게시물의 id
    public List<Comments> getComments(@PathVariable("idx") Long id) {
        System.out.println(id);
        List<Comments> comments = new ArrayList<>();
        List<Comments> temp = CommentsRepository.findAll();
        for (Comments comment : temp) {
            if (comment.getContentsId().equals(id)) {
                comments.add(comment);
            }
        }
        System.out.println(comments.size());
        return comments;
        // commentRepository = 댓글 저장소 / findbyid(게시물의 id와 댓글의 id가 동일한 것을 찾아줘)
//        return CommentsRepository.findById(id).orElseThrow(
//                ()->new IllegalArgumentException("commentsId가 존재하지 않습니다."));
    }

    // 게시글 생성
    @PostMapping("/api/comments")
    public Comments createComments(@RequestBody CommentRequestDto requestDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Comments comments = new Comments(requestDto, userDetails);
        return CommentsRepository.save(comments);
    }


    // 게시글 수정
    @PutMapping("/api/comments/{id}")
    public Long updateComments(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        CommentsService.update(id, requestDto);
        return id;
    }

    // 게시글 삭제
    @DeleteMapping("/api/comments/{id}")
    public Long deleteComments(@PathVariable Long id) {
        CommentsRepository.deleteById(id);
        return id;
    }
}