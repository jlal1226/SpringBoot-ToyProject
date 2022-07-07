package com.toyproject.competition.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class MemberFormDto {

    @NotBlank(message = "이름을 입력해주세요")
    private String memberName;

    @NotBlank(message = "아이디를 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @NotBlank(message = "나이를 입력해주세요")
    private String age;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

}
