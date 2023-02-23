package com.example.searchbooks.repository;

import com.example.searchbooks.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeywordRepository extends JpaRepository<Keyword,Long>{
}
