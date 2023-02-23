package com.example.searchbooks.repository;

import com.example.searchbooks.model.Author;
import com.example.searchbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long> {

    List<Author>findByBook(Book id);

    @Query("SELECT a FROM Author a WHERE a.name like %:keyword% OR a.surname like %:keyword%")
    List<Author> searchKeyword(@Param("keyword") String keyword);

//    @Query("SELECT a FROM Author a WHERE a.surname like %:keyword%")
//    List<Author> searchKeywordInSurname(@Param("keyword") String keyword);
}
