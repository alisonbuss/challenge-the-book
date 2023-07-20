package com.project.service.webapi.impls.services;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.service.webapi.domain.entities.Book;
import com.project.service.webapi.domain.repositories.IBookRepository;
import com.project.service.webapi.domain.services.IBookService;

import jakarta.transaction.Transactional;

@Service
public class BookService implements IBookService {

    final IBookRepository bookRepository;

    public BookService(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean existsByBookId(UUID bookId) {
        return bookRepository.existsById(bookId);
    }

    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }

    public Optional<Book> findByBookId(UUID bookId) {
        return bookRepository.findById(bookId);
    }

    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findAllPageable(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Transactional
    public Book add(Book newbook) {
        var bookCurrent = bookRepository.save(newbook);

        return bookCurrent;
    }

    @Transactional
    public void delete(Book BookModel) {
        bookRepository.delete(BookModel);
    }

}
