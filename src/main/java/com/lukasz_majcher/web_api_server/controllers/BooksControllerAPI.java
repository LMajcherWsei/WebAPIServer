package com.lukasz_majcher.web_api_server.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lukasz_majcher.web_api_server.data.BooksRepository;
import com.lukasz_majcher.web_api_server.models.Book;

@RestController
@RequestMapping("/books/api")
public class BooksControllerAPI {
    // @Autowired
    private BooksRepository booksData;

    public BooksControllerAPI(BooksRepository repo) {
        super();
        booksData = repo;
    }

    @GetMapping(value = "/list")
    public List<Book> books() {
        List<Book> allBooks = booksData.findAll();
        return allBooks;
    }

    @PostMapping(value = "/save")
    public Book save(@RequestBody Book book) {
        if (book != null) {
            booksData.save(book);
        }
        return book;
    }

    @GetMapping(value = "/edit/{id}")
    public Book editBook(@PathVariable long id) {
        Optional<Book> book = booksData.findById(id);

        if (book != null) {
            return book.get();
        } else {
            return books().get(0);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public Boolean deleteBook(@PathVariable long id) {
        Optional<Book> book = booksData.findById(id);

        if (book != null) {
            booksData.delete(book.get());
            return true;
        }
        return false;
    }
}
