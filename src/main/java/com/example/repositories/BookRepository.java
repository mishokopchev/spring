package com.example.repositories;

import com.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    public Book findBookByName(String name);
    public Book findBookByAuthorAndName(String author,String name);

}
