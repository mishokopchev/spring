package com.example.dto;

import com.fasterxml.jackson.annotation.*;

/**
 * Created by mihailkopchev on 3/6/18.
 */
public class BookDTO {

    private String name;
    private String author;

    @JsonIgnore
    private String code;

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BookDTO() {

    }

    @JsonCreator
    public BookDTO(@JsonProperty("name") String name, @JsonProperty("author") String author) {
        this.name = name;
        this.author = author;
    }
}
