package com.codestates.domain.loanhistory;

import com.codestates.domain.book.Book;
import com.codestates.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long> {

    Optional<LoanHistory> findByBookAndUser(Book book, User user);

    Optional<LoanHistory> findByBook_IdAndUser_Id(Long bookId, Long userId);

    List<LoanHistory> findAllByUser_Id(Long userId);

}
