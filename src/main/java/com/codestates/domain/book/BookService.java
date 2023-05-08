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
        
        verifyOnLoanCount(user);
        
        verifyOverdue(user);
        
        verifyBookStatus(book);
        
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
        
        book.setBookStatus(Book.BookStatus.AVAILABLE);

        return foundLoanHistory;
    }

    private void verifyOnLoanCount(User user) {
        long onLoanCount = user.getLoanHistories()
                .stream()
                .filter(loanHistory -> loanHistory.getReturnedAt() == null)
                .count();

        if (onLoanCount >= MAX_LOAN) {
            throw new RuntimeException("대출은 5권까지 가능합니다.");
        }
    }

    private void verifyOverdue(User user) {
        boolean isOverdue = user.getLoanHistories()
                .stream()
                .anyMatch(loanHistory -> loanHistory.getReturnedAt() == null &&
                        LocalDateTime.now().isAfter(loanHistory.getLoanedAt().plusDays(14)));

        if (isOverdue) {
            throw new RuntimeException("연체된 책이 하나라도 있으면 대출할 수 없습니다.");
        }
    }

    private void verifyBookStatus(Book book) {
        if (book.getBookStatus().equals(Book.BookStatus.ON_LOAN)) {
            throw new RuntimeException("해당 책은 이미 대출 중입니다.");
        }
    }

}
