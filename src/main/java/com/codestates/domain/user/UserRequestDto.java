package com.codestates.domain.user;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class UserRequestDto {

    @NotBlank(message = "이름은 필수 입력 사항입니다.")
    private String name;

    @Pattern(regexp = "^010-\\d{4}-\\d{4}$",
            message = "휴대전화 번호는 010-1234-5678 형태로 작성해 주세요.")
    private String phone;

}
