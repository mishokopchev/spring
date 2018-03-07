package com.example.model;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by mihailkopchev on 3/6/18.
 */
@Entity
public class Book implements Serializable{
    @Id
    private String code;
    private String author;
    private String name;

    public Book(String code, String author, String name) {
        this.code = code;
        this.author = author;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book() {

    }
}
