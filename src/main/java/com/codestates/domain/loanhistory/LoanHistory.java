package com.codestates.domain.loanhistory;

import com.codestates.domain.book.Book;
import com.codestates.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class LoanHistory {

    // todo: BaseEntity 추가

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime loanedAt;

    @Setter
    private LocalDateTime returnedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public LoanHistory(LocalDateTime loanedAt, LocalDateTime returnedAt, User user, Book book) {
        this.loanedAt = loanedAt;
        this.returnedAt = returnedAt;
        this.user = user;
        this.book = book;
    }

}
