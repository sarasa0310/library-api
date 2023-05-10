package com.codestates.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {

    USER_NOT_FOUND(404, "존재하지 않는 사용자입니다."),

    USER_ALREADY_EXIST(409, "이미 존재하는 사용자명과 휴대전화 번호 조합입니다."),

    ON_LOAN(409, "대출 중인 책이 있을 경우 삭제 할 수 없습니다."),

    BOOK_NOT_FOUND(404, "존재하지 않는 책입니다."),

    MAX_LOAN_EXCEEDED(409, "대출은 5권까지 가능합니다."),

    OVERDUE(409, "연체된 책이 하나라도 있으면 대출할 수 없습니다."),

    ALREADY_ON_LOAN(409, "해당 책은 이미 대출 중입니다."),

    LOAN_HISTORY_NOT_FOUND(404, "대출 기록이 존재하지 않습니다");

    private final int status;

    private final String message;

}
