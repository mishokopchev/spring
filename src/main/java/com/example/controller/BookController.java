package com.example.controller;


import com.example.dto.BookDTO;
import com.example.exception.ApplicationException;
import com.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by mihailkopchev on 3/6/18.
 */
@RestController
@RequestMapping(value = "book")
@Component
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/add",consumes = "application/json")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) throws ApplicationException {
        this.bookService.addBook(bookDTO);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping(value = "/{code}",produces = "application/json")
    public ResponseEntity<BookDTO> getBook(@RequestParam String code){
        System.out.println(this.bookService);
        return new ResponseEntity<BookDTO>(HttpStatus.OK);
    }




}
