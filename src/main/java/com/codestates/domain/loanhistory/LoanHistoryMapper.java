package com.codestates.domain.loanhistory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoanHistoryMapper {

    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "book.title", target = "bookTitle")
    LoanHistoryResponseDto loanHistoryToLoanHistoryResponseDto(LoanHistory loanHistory);

}
