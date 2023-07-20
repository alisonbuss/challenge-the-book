package com.project.service.webapi.controllers;

import java.util.UUID;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lowagie.text.DocumentException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.service.webapi.core.export.BookExportPDF;
import com.project.service.webapi.domain.entities.Book;
import com.project.service.webapi.dtos.requests.BookRequestDTO;
import com.project.service.webapi.impls.services.BookService;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin(origins = "*", maxAge = 4800)
public class BooksController {

    private Logger logger = LogManager.getLogger(BooksController.class);
    private static final String RESPONSE_NOT_FOUND = "User not found.";

    @Autowired
	BookService bookService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            logger.info("INFO - GET /books");

            var response = bookService.findAll();

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Book>> getAllBooksPageable(@PageableDefault(
        page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC
    ) Pageable pageable) {
        try {
            logger.info("INFO - GET /books/pageable");

            var response = bookService.findAllPageable(pageable);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") UUID id){
        try {
            logger.info("INFO - GET /books/{id}");

            Optional<Book> bookOptional = bookService.findByBookId(id);
            if (!bookOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RESPONSE_NOT_FOUND);
            }

            return ResponseEntity.status(HttpStatus.OK).body(bookOptional.get());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> createNewBook(@RequestBody @Valid BookRequestDTO bookRequestDTO) {
		try {
            logger.info("INFO - POST /books");

            var newBook = new Book();
		    BeanUtils.copyProperties(bookRequestDTO, newBook);
            var response = bookService.add(newBook);

		    return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOneBook(@PathVariable(value = "id") UUID id){
        try {
            logger.info("INFO - DELETE /books/{id}");

            Optional<Book> bookOptional = bookService.findByBookId(id);
            if (!bookOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RESPONSE_NOT_FOUND);
            }

            bookService.delete(bookOptional.get());

            return ResponseEntity.status(HttpStatus.OK).body("Book deleted successfully.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/export/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        logger.info("INFO - GET /export/pdf");

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=books_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        var books = bookService.findAll();

        BookExportPDF exporter = new BookExportPDF(books);
        exporter.export(response);
    }

}
