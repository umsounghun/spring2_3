package com.eungsoo.blog.controller;

import com.eungsoo.blog.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
            return "index";
        }
        model.addAttribute("username", userDetails.getUsername());
        return "index";
    }

    @GetMapping("/detail.html")
    public String detail(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // userDetails = 유저 권한 USER 들어잇음 / 로그인 안하면 비어있으므로 NULL
        if (userDetails == null) {
            return "detail";
        }
        model.addAttribute("username", userDetails.getUsername());
        return "detail";
    }

    @GetMapping("/write")
    public String write(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // userDetails = 유저 권한 USER 들어잇음 / 로그인 안하면 비어있으므로 NULL
        if (userDetails == null) {
            return "write";
        }
        model.addAttribute("username", userDetails.getUsername());
        return "write";
    }
}