package com.codestates.domain.loanhistory;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoanHistoryMapper {

    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "book.title", target = "bookTitle")
    LoanHistoryResponseDto loanHistoryToLoanHistoryResponse(LoanHistory loanHistory);

    List<LoanHistoryResponseDto> loanHistoriesToLoanHistoryResponses(List<LoanHistory> loanHistories);

}
