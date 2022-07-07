package com.toyproject.competition.service;

import com.toyproject.competition.domain.Member;
import com.toyproject.competition.dto.MemberFormDto;
import com.toyproject.competition.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(MemberFormDto memberFormDto) {

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(memberFormDto.getPassword());

        // 엔티티 생성
        Member member = Member.builder()
                .name(memberFormDto.getMemberName())
                .username(memberFormDto.getUsername())
                .password(encodedPassword)
                .age(Integer.parseInt(memberFormDto.getAge()))
                .role("ROLE_USER")
                .build();

        memberRepository.save(member);

        return member.getId();
    }
    /**
     * 아이디 중복 체크
     */

    /**
     * 유효성 검사 처리
     */
    public Map<String, String> validateHandling(BindingResult bindingResult) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", fieldError.getField());
            validatorResult.put(validKeyName, fieldError.getDefaultMessage());
        }
        return validatorResult;
    }

    /**
     * 회원 조회
     */
    public Member findMember(String username) {
        return memberRepository.findByUsername(username);
    }

}
