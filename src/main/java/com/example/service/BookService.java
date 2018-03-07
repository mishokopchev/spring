package com.example.service;

import com.example.dto.BookDTO;
import com.example.exception.ApplicationException;
import com.example.model.Book;
import com.example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
 * Created by mihailkopchev on 3/6/18.
 */
@Service
public class BookService implements BackendService{
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(BookDTO bookDTO) throws ApplicationException {

        try {
            String code = this.createHash(bookDTO.getName());
            Book dbBook = this.bookRepository.findOne(code);
            if(dbBook != null){
                throw new ApplicationException("Book already in the store");
            }
            Book book = new Book();
            book.setAuthor(bookDTO.getAuthor());
            book.setName(bookDTO.getName());
            book.setCode(code);
            this.bookRepository.save(book);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public void update(String code, BookDTO bookDTO) {

    }

    public void delete(String code) {

    }

    public Book findOne(String code) {
        return this.bookRepository.findOne(code);
    }

    List<BookDTO> getAllBooks() {
        return null;

    }

    private String createHash(String var){
        return DigestUtils.md5DigestAsHex(var.getBytes());
    }



}
