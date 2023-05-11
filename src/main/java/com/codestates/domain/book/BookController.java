package com.codestates.domain.book;

import com.codestates.domain.loanhistory.LoanHistory;
import com.codestates.domain.loanhistory.LoanHistoryMapper;
import com.codestates.domain.loanhistory.LoanHistoryResponseDto;
import com.codestates.pagination.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final LoanHistoryMapper loanHistoryMapper;

    @GetMapping
    public ResponseEntity<?> searchBook(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(required = false) String title) {
        Page<Book> bookPage = bookService.searchBook(page - 1, size, title);

        List<Book> books = bookPage.getContent();

        return ResponseEntity.ok().body(new MultiResponseDto<>(books, bookPage));
    }

    @PostMapping("/loan")
    public ResponseEntity<?> loanBook(@RequestBody @Valid LoanDto loanDto) {
        LoanHistory loanHistory = bookService.loanBook(loanDto);

        LoanHistoryResponseDto loanHistoryResponse =
                loanHistoryMapper.loanHistoryToLoanHistoryResponse(loanHistory);

        return ResponseEntity.created(
                URI.create("/loanHistories/" + loanHistory.getId()))
                .body(loanHistoryResponse);
    }

    @PatchMapping("/return")
    public ResponseEntity<?> returnBook(@RequestBody @Valid LoanDto loanDto) {
        LoanHistory loanHistory = bookService.returnBook(loanDto);

        LoanHistoryResponseDto loanHistoryResponse =
                loanHistoryMapper.loanHistoryToLoanHistoryResponse(loanHistory);

        return ResponseEntity.ok().body(loanHistoryResponse);
    }

}
