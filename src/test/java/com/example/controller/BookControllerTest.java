package com.example.controller;

import com.example.repositories.BookRepository;
import com.example.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mihailkopchev on 3/6/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Test
    public void test(){
        assertNotNull(bookController);
    }

}
