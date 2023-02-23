package com.example.searchbooks.controller;

import com.example.searchbooks.dto.AuthorBookRequest;
import com.example.searchbooks.dto.KeywordDTO;
import com.example.searchbooks.model.Author;
import com.example.searchbooks.model.Book;
import com.example.searchbooks.model.Keyword;
import com.example.searchbooks.repository.AuthorRepository;
import com.example.searchbooks.repository.BookRepository;
import com.example.searchbooks.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class WebController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @GetMapping("/books")
    public List<Book> showAllBooks(){
        return bookRepository.findAll();
    }

    @GetMapping("/authors")
    public List<Author> showAllAuthors(){
        return authorRepository.findAll();
    }

    @PostMapping("/books/add")
    public ResponseEntity<?> createBook(@RequestBody Book book){
        Book newBook = bookRepository.save(book);
        return ResponseEntity.ok(newBook);
    }

    @PostMapping("/authors/add")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorBookRequest request){
        Author author = request.getAuthor();
        Book book = request.getBook();
        author.setBook(book);
        return ResponseEntity.ok(authorRepository.save(author));
    }

    @PostMapping("/search")
    public List<?> searchByKeyword(@RequestBody KeywordDTO keyword){
        if(bookRepository.searchKeyword(keyword.getKeyword()).isEmpty()){
            List<Author> listOfAuthors= authorRepository.searchKeyword(keyword.getKeyword());
            for (Author author:listOfAuthors) {
                System.out.println(author.getBook().getId());
                Book foundBook = author.getBook();
                return Collections.singletonList(bookRepository.findById(foundBook.getId()));
            }
        }else {
        return bookRepository.searchKeyword(keyword.getKeyword());
        }
        Keyword keyword1 = new Keyword();
        keyword1.setKeyword(keyword.getKeyword());
        keywordRepository.save(keyword1);
        return keywordRepository.findAll();
    }

    @PostMapping("/authors/find")
    public List<Author>findBy(@RequestBody Book id){
        return authorRepository.findByBook(id);
    }
}
