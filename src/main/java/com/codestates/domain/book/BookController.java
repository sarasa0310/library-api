package com.codestates.domain.book;

import com.codestates.domain.loanhistory.LoanHistory;
import com.codestates.pagination.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> searchBook(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(required = false) String title) {
        Page<Book> bookPage = bookService.searchBook(page - 1, size, title);

        List<Book> books = bookPage.getContent();

        return ResponseEntity.ok().body(new MultiResponseDto<>(books, bookPage));
    }

    @PostMapping("/{book-id}/loan")
    public ResponseEntity<?> loanBook(@PathVariable("book-id") Long bookId,
                                      @RequestParam Long userId) {
        LoanHistory loanHistory = bookService.loanBook(bookId, userId);

        return new ResponseEntity<>(loanHistory, HttpStatus.CREATED);
    }

    @PatchMapping("/{book-id}/return")
    public ResponseEntity<?> returnBook(@PathVariable("book-id") Long bookId,
                                        @RequestParam Long userId) {
        LoanHistory loanHistory = bookService.returnBook(bookId, userId);

        return ResponseEntity.ok().body(loanHistory);
    }

}
