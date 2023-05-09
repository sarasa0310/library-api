package com.codestates.domain.book;

import com.codestates.domain.user.UserRequestDto;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LoanDto {

    private UserRequestDto userInfo;

    @NotBlank(message = "도서 제목은 필수 입력 사항입니다.")
    private String title;

}
