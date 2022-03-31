package com.eungsoo.blog.controller;


import com.eungsoo.blog.dto.CommentResponseDto;
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

    @GetMapping("/api/comments/{idx}")
    // Long id = 게시물의 id
    public List<CommentResponseDto> getComments(@PathVariable("idx") Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<CommentResponseDto> comments = new ArrayList<>();
        List<Comments> temp = CommentsRepository.findAll();
        for (Comments comment : temp) {
            if (comment.getContentsId().equals(id)) {
                if (userDetails.getUser().getId().equals(comment.getUserId())){
                    CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getName(), comment.getComments(), comment.getModifiedAt(), comment.getId(), true);
                    comments.add(commentResponseDto);
                    continue;
                }
                CommentResponseDto commentResponseDto = new CommentResponseDto(comment.getName(), comment.getComments(), comment.getModifiedAt(), comment.getId(), false);
                comments.add(commentResponseDto);
            }
        }
        return comments;
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