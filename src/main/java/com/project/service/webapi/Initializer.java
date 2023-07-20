package com.project.service.webapi;

import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.project.service.webapi.domain.entities.Book;
import com.project.service.webapi.domain.entities.Chapter;
import com.project.service.webapi.domain.entities.Page;
import com.project.service.webapi.domain.repositories.IBookRepository;

@Component
public class Initializer implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LogManager.getLogger(Initializer.class);

    @Autowired
    IBookRepository bookRepository;

    private void createFirstBook() {
        logger.info("Create first book...");

        // Create Book:
        Book newBook = new Book();
        newBook.setTitle("Aaaaaa...");

        // Create first chapter:
        var chapterFirst = new Chapter();
        chapterFirst.setChapter("First Chapter Aaaa...");

        // Create first chapter pages:
        Page chapterFirstPage01 = new Page();
        chapterFirstPage01.setContent("Content page Aaaaaaaa...");
        chapterFirst.getPages().add(chapterFirstPage01);

        Page chapterFirstPage02 = new Page();
        chapterFirstPage02.setContent("Content page Bbbbbbbb...");
        chapterFirst.getPages().add(chapterFirstPage02);

        Page chapterFirstPage03 = new Page();
        chapterFirstPage03.setContent("Content page Cccccccc...");
        chapterFirst.getPages().add(chapterFirstPage03);

        // Save Book:
        var chapters = new ArrayList<Chapter>();
        chapters.add(chapterFirst);

        newBook.setChapters(chapters);

        this.bookRepository.save(newBook);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("Initializer ApplicationEvent...");

        createFirstBook();
    }

}