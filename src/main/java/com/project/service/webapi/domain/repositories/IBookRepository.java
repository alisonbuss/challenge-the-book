package com.project.service.webapi.domain.repositories;

import java.util.UUID;
import java.util.Optional;

import com.project.service.webapi.domain.entities.Book;

public interface IBookRepository extends IRepositoryBase<Book, UUID> {

    boolean existsByBookId(UUID bookId);
    boolean existsByTitle(String title);

    Optional<Book> findByBookId(UUID bookId);
    Optional<Book> findByTitle(String title);

}
