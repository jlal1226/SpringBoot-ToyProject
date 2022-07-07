package com.toyproject.competition.controller;

import com.toyproject.competition.dto.LoginFormDto;
import com.toyproject.competition.dto.MemberFormDto;
import com.toyproject.competition.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원가입
     */
    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberFormDto());
        return "joinForm";
    }

    @PostMapping("/join")
    public String joinMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {
        // 회원 가입 실패시
        if (bindingResult.hasErrors()) {

            model.addAttribute("memberForm", memberFormDto);

            Map<String, String> validatorResult = memberService.validateHandling(bindingResult);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            return "joinForm";
        }

        // 회원 정보 저장 후 로그인페이지로 이동
        memberService.join(memberFormDto);


        return "redirect:/loginForm";
    }

    /**
     * 로그인
     */
    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginFormDto());
        return "loginForm";
    }

    @GetMapping("login")
    public String login() {
        return "loginForm";
    }
}
