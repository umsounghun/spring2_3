package com.eungsoo.blog.controller;


//import com.eungsoo.blog.dto.UserRequestDto;
import com.eungsoo.blog.models.Contents;
import com.eungsoo.blog.repository.ContentsRepository;
import com.eungsoo.blog.models.ContentsRequestDto;
import com.eungsoo.blog.service.ContentsService;
//import com.eungsoo.blog.service.UserService;
import lombok.RequiredArgsConstructor;
//import org.springframework.ui.Model;
//import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;
//import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ContentsRestController {

    private final ContentsRepository ContentsRepository;
    private final ContentsService ContentsService;

    // 게시글 전체 조회
    @GetMapping("/api/contents")
    public List<Contents> getContents() {
        return ContentsRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 특정 조회
    @GetMapping("/api/contents/{id}")
    public Contents getContents(@PathVariable Long id) {
        Contents contents =  ContentsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        return contents;
    }

    // 게시글 생성
    @PostMapping("/api/contents")
    public Contents createContents(@RequestBody ContentsRequestDto requestDto) {
        Contents Contents = new Contents(requestDto);
        return ContentsRepository.save(Contents);
    }

    // 게시글 수정
    @PutMapping("/api/contents/{id}")
    public Long updateContents(@PathVariable Long id, @RequestBody ContentsRequestDto requestDto) {
        ContentsService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/contents/{id}")
    public Long deleteContents(@PathVariable Long id) {
        ContentsRepository.deleteById(id);
        return id;
    }

//    @GetMapping("/auth/join")
//    public String join() {
//        return "/user/user-join";
//    }

//    /* 회원가입 */
//    @PostMapping("/auth/joinProc")
//    public String joinProc(@Valid UserRequestDto userDto, Errors errors, Model model) {
//
//        if (errors.hasErrors()) {
//            /* 회원가입 실패시 입력 데이터 값을 유지 */
//            model.addAttribute("userDto", userDto);
//
//            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
//            Map<String, String> validatorResult = UserService.validateHandling(errors);
//            for (String key : validatorResult.keySet()) {
//                model.addAttribute(key, validatorResult.get(key));
//            }
//            /* 회원가입 페이지로 다시 리턴 */
//            return "/user/user-join";
//        }
//        UserService.userJoin(userDto);
//        return "redirect:/auth/login";
//    }


}