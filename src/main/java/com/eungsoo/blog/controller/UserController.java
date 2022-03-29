package com.eungsoo.blog.controller;

import com.eungsoo.blog.dto.SignupRequestDto;
import com.eungsoo.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    /* 회원가입 */ // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(@ModelAttribute @Valid SignupRequestDto signupRequestDto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
//            아이디 중복 검사
//            userService.checkUsernameDuplication(signupRequestDto);
            /* 회원가입 페이지로 다시 리턴 */
            return "signup";
        }
        userService.registerUser(signupRequestDto);
        return "redirect:/user/login";
    }
}