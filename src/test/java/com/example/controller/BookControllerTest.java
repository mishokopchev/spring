package com.example.controller;


import com.example.dto.BookDTO;
import com.example.exception.ApplicationException;
import com.example.model.Book;
import com.example.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by mihailkopchev on 3/6/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testContext() {
        assertThat(bookService).isNotNull();
    }

    /*
    @Test
    public void addBook() throws ApplicationException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("Name name");
        bookDTO.setAuthor("Author author");
        when(bookService.addBook(bookDTO)).thenReturn()
    }

    */

    @Test
    public void findBookStatusOK() throws Exception {
        Book book = buildBook("1234","Irobot","azimov");
        given(this.bookService.findOne("1234")).willReturn(book);
        this.mockMvc.perform(get("/book/1234")).andExpect(status().isOk());
    }

    @Test
    public void findBookReturnOK() throws Exception {
        Book book = buildBook("1234","Irobot","azimov");
        given(this.bookService.findOne("1234")).willReturn(book);
        MvcResult response = this.mockMvc.perform(get("/book/1234")).andReturn();
        Book returnedBook = objectMapper.readValue(response.getResponse().getContentAsString(),Book.class);
        Assert.assertEquals(book,returnedBook);
    }

    @Test
    public void fromEntityToDto(){
        Book book = buildBook(null,"Irobot","azimov");
        BookDTO bookDTO = modelMapper.map(book,BookDTO.class);
        Assert.assertEquals(bookDTO.getAuthor(),book.getAuthor());
        Assert.assertEquals(bookDTO.getName(),book.getName());
    }

    private Book buildBook(String code, String name,String author){
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setCode(code);
        return book;
    }


}
