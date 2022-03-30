package com.eungsoo.blog.service;

import com.eungsoo.blog.models.Contents;
import com.eungsoo.blog.repository.ContentsRepository;
import com.eungsoo.blog.models.ContentsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ContentsService {

    private final ContentsRepository ContentsRepository;

    @Transactional
    public Long update(@PathVariable Long id, @RequestBody ContentsRequestDto requestDto) {
        Contents Contents = ContentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Contents.update(requestDto);
        return Contents.getId();
    }

    //    // 글쓰기 작업
//    public Contents createContents(ContentsRequestDto requestDto, Long userId) {
//        // 요청받은 DTO 로 DB에 저장할 객체 만들기
//        Contents contents = new Contents(requestDto, userId);
//
//        ContentsRepository.save(contents);
//
//        return contents;
//    }

}