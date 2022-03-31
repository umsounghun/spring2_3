package com.eungsoo.blog.service;

import com.eungsoo.blog.models.CommentRequestDto;
import com.eungsoo.blog.models.Comments;
import com.eungsoo.blog.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository CommentsRepository;

    @Transactional
    public void update(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        Comments Comments = CommentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Comments.update(requestDto);
    }
    }