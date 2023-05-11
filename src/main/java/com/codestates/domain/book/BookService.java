package com.codestates.domain.book;

import com.codestates.domain.loanhistory.LoanHistory;
import com.codestates.domain.loanhistory.LoanHistoryRepository;
import com.codestates.domain.user.User;
import com.codestates.domain.user.UserService;
import com.codestates.exception.BusinessLogicException;
import com.codestates.exception.ExceptionCode;
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

    private final UserService userService;

    private final LoanHistoryRepository loanHistoryRepository;

    private static final Integer MAX_LOAN = 5;

    @Transactional(readOnly = true)
    public Page<Book> searchBook(int page, int size, String title) {
        if (title != null) {
            return bookRepository.findByTitleContaining(title, PageRequest.of(page, size, Sort.by("title")));
        }

        return bookRepository.findAll(PageRequest.of(page, size, Sort.by("title")));
    }

    public LoanHistory loanBook(LoanDto loanDto) {
        Book book = findVerifiedBook(loanDto.getTitle());
        User user = userService.findVerifiedUser(loanDto.getName(), loanDto.getPhone());

        verifyOnLoanCount(user);
        
        verifyOverdue(user);
        
        verifyBookStatus(book);
        
        book.setBookStatus(Book.BookStatus.ON_LOAN);

        LoanHistory newLoanHistory =
                new LoanHistory(LocalDateTime.now(), null, user, book);

        return loanHistoryRepository.save(newLoanHistory);
    }

    public LoanHistory returnBook(LoanDto loanDto) {
        Book book = findVerifiedBook(loanDto.getTitle());
        User user = userService.findVerifiedUser(loanDto.getName(), loanDto.getPhone());

        LoanHistory foundLoanHistory = findVerifiedLoanHistory(book, user);

        foundLoanHistory.setReturnedAt(LocalDateTime.now());
        
        book.setBookStatus(Book.BookStatus.AVAILABLE);

        return foundLoanHistory;
    }

    private Book findVerifiedBook(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOOK_NOT_FOUND));
    }

    private LoanHistory findVerifiedLoanHistory(Book book, User user) {
        return loanHistoryRepository.findByBookAndUserAndReturnedAtNull(book, user)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.LOAN_HISTORY_NOT_FOUND));
    }

    private void verifyOnLoanCount(User user) {
        long onLoanCount = user.getLoanHistories()
                .stream()
                .filter(loanHistory -> loanHistory.getReturnedAt() == null)
                .count();

        if (onLoanCount >= MAX_LOAN) {
            throw new BusinessLogicException(ExceptionCode.MAX_LOAN_EXCEEDED);
        }
    }

    private void verifyOverdue(User user) {
        boolean isOverdue = user.getLoanHistories()
                .stream()
                .anyMatch(loanHistory -> loanHistory.getReturnedAt() == null &&
                        LocalDateTime.now().isAfter(loanHistory.getLoanedAt().plusDays(14)));

        if (isOverdue) {
            throw new BusinessLogicException(ExceptionCode.OVERDUE);
        }
    }

    private void verifyBookStatus(Book book) {
        if (book.getBookStatus().equals(Book.BookStatus.ON_LOAN)) {
            throw new BusinessLogicException(ExceptionCode.ALREADY_ON_LOAN);
        }
    }

}
