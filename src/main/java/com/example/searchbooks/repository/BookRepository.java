package com.example.searchbooks.repository;


import com.example.searchbooks.dto.KeywordDTO;
import com.example.searchbooks.model.Author;
import com.example.searchbooks.model.Book;
import com.example.searchbooks.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("SELECT b FROM Book b WHERE b.title like %:keyword% OR b.year like %:keyword%")
    List<Book> searchKeyword(@Param("keyword") String keyword);


}
