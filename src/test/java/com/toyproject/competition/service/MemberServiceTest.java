package com.toyproject.competition.service;

import com.toyproject.competition.dto.MemberFormDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void 회원가입_테스트 () throws Exception {
        // given
        MemberFormDto memberFormDto = MemberFormDto.builder()
                .memberName("김정락")
                .username("test")
                .password("asdf")
                .email("test@naver.com")
                .age("26")
                .build();

        // when
        Long id = memberService.join(memberFormDto);

        // then
        System.out.println(id);
    }
}