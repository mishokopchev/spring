package com.example.controller;


import com.example.dto.BookDTO;
import com.example.exception.ApplicationException;
import com.example.model.Book;
import com.example.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * Created by mihailkopchev on 3/6/18.
 */
@RestController
@RequestMapping(value = "books")
@Component
public class BookController {

    private BookService bookService;
    private ModelMapper modelMapper;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) throws ApplicationException {
        Book book = this.modelMapper.map(bookDTO, Book.class);
        this.bookService.addBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{code}", produces = "application/json")
    public ResponseEntity<?> getBook(@PathVariable(required = true) String code) {
        Book book = this.bookService.findOne(code);
        if (book == null) {
            String message = "Book Not found!";
            return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
        }
        BookDTO bookDTO = null;
        bookDTO = modelMapper.map(book, BookDTO.class);
        return new ResponseEntity<BookDTO>(bookDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        List<BookDTO> bookDTOList =
                modelMapper.map(bookList, new TypeToken<List<BookDTO>>() {
                }.getType());
        return new ResponseEntity<List<?>>(bookDTOList, HttpStatus.OK);
    }


}
