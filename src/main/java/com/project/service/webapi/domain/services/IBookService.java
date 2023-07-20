package com.project.service.webapi.domain.services;

import java.util.UUID;
import java.util.Optional;

import com.project.service.webapi.domain.entities.Book;

public interface IBookService extends IServiceBase<Book>  {

    boolean existsByBookId(UUID bookId);
    boolean existsByTitle(String title);

    Optional<Book> findByBookId(UUID bookId);
    Optional<Book> findByTitle(String title);

    Book add(Book newBook);
    void delete(Book book);

}
