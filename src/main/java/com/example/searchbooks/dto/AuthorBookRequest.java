package com.example.searchbooks.dto;

import com.example.searchbooks.model.Author;
import com.example.searchbooks.model.Book;

public class AuthorBookRequest {
    private Author author;
    private Book book;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
