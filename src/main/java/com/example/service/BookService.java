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
            Book book = new Book();
            book.setAuthor(bookDTO.getAuthor());
            book.setName(bookDTO.getName());
            byte[] array = bookDTO.getName().getBytes();
            book.setCode(DigestUtils.md5DigestAsHex(array));

            this.bookRepository.save(book);
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        }
    }

    public void update(String code, BookDTO bookDTO) {

    }

    public void delete(String code) {

    }

    public BookDTO getOne(String code) {
        return null;
    }

    List<BookDTO> getAllBooks() {
        return null;
    }



}
