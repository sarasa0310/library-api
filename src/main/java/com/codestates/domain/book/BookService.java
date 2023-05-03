package com.codestates.domain.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public Page<Book> searchBook(int page, int size, String title) {
        if (title != null) {
            return bookRepository.findByTitleContaining(title, PageRequest.of(page, size, Sort.by("title")));
        }

        return bookRepository.findAll(PageRequest.of(page, size, Sort.by("title")));
    }

}
