package com.codestates.domain.book;

import com.codestates.domain.loanhistory.LoanHistory;
import com.codestates.domain.loanhistory.LoanHistoryRepository;
import com.codestates.domain.user.User;
import com.codestates.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

    private final LoanHistoryRepository loanHistoryRepository;

    private static final Integer MAX_LOAN = 5;

    @Transactional(readOnly = true)
    public Page<Book> searchBook(int page, int size, String title) {
        if (title != null) {
            return bookRepository.findByTitleContaining(title, PageRequest.of(page, size, Sort.by("title")));
        }

        return bookRepository.findAll(PageRequest.of(page, size, Sort.by("title")));
    }

    public LoanHistory loanBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        // todo: 연체중인 책이 있으면 대출 불가능 추가
        // todo: Base Entity 추가

        // 대출은 5권까지 가능. 5권 이상 대출하려고 하는지 체크
        checkOnLoanCount(user);

        // 해당 책이 대출가능한지 체크
        checkBookStatus(book);

        // 대출 중으로 변경
        book.setBookStatus(Book.BookStatus.ON_LOAN);

        LoanHistory newLoanHistory =
                new LoanHistory(LocalDateTime.now(), null, user, book);

        return loanHistoryRepository.save(newLoanHistory);
    }

    public LoanHistory returnBook(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        LoanHistory foundLoanHistory =
                loanHistoryRepository.findByBookAndUser(book, user).orElseThrow();

        foundLoanHistory.setReturnedAt(LocalDateTime.now());

        // 대출 가능으로 변경
        book.setBookStatus(Book.BookStatus.AVAILABLE);

        return foundLoanHistory;
    }

    private void checkOnLoanCount(User user) {
        long onLoanCount = user.getLoanHistories()
                .stream()
                .filter(loanHistory -> loanHistory.getReturnedAt() == null)
                .count();

        if (onLoanCount >= MAX_LOAN) {
            throw new RuntimeException("대출은 5권까지 가능합니다.");
        }
    }

    private void checkBookStatus(Book book) {
        if (book.getBookStatus().equals(Book.BookStatus.ON_LOAN)) {
            throw new RuntimeException("해당 책은 이미 대출 중입니다.");
        }
    }

}
