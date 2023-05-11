package com.codestates.domain.loanhistory;

import com.codestates.domain.book.Book;
import com.codestates.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanHistoryRepository extends JpaRepository<LoanHistory, Long> {

    Optional<LoanHistory> findByBookAndUserAndReturnedAtNull(Book book, User user);

    List<LoanHistory> findAllByUser(User user);

}
