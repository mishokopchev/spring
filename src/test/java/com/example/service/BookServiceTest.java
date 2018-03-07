package com.example.service;

import com.example.Demo2Application;
import com.example.H2TestProfileJpaConfig;
import com.example.model.Book;
import com.example.repositories.BookRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mihailkopchev on 3/7/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Demo2Application.class,H2TestProfileJpaConfig.class})
@ActiveProfiles("test")
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void addBookTest(){
        Book book = new Book();
        book.setCode("1234");
        book.setName("Azimov");
        bookRepository.saveAndFlush(book);

        Book dbbook = bookRepository.findOne(book.getCode());
        Assert.assertEquals(book,dbbook);
    }
}
