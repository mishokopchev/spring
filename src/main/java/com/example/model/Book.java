package com.example.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by mihailkopchev on 3/6/18.
 */
@Entity
public class Book implements Serializable {
    @Id
    private String code;
    private String author;
    private String name;

    public Book(String code, String author, String name) {
        this.setCode(author + name);
        this.author = author;
        this.name = name;
    }
    @JsonGetter
    public String getCode() {
        return code;
    }

    @JsonSetter
    public void setCode(String code) {
        if (code != null) {
            this.code = DigestUtils.md5DigestAsHex(code.getBytes());
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (code != null ? !code.equals(book.code) : book.code != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        return name != null ? name.equals(book.name) : book.name == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
