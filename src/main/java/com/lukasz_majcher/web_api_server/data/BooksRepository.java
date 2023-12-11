package com.lukasz_majcher.web_api_server.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lukasz_majcher.web_api_server.models.Book;

public interface BooksRepository extends JpaRepository<Book, Long> {

}
