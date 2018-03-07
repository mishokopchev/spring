package com.example.controller;

import com.example.repositories.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mihailkopchev on 3/6/18.
 */

@RestController
public class SimpleController {

    @RequestMapping("/home")
    String home() {
        return "Hello World!";

    }



}
