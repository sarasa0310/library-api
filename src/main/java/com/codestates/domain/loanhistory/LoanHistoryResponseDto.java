package com.codestates.domain.loanhistory;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class LoanHistoryResponseDto {

    private Long id;

    private LocalDateTime loanedAt;

    private LocalDateTime returnedAt;

    private String userName;

    private String bookTitle;

}
