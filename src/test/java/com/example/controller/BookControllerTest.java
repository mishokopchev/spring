package com.example.controller;


import com.example.dto.BookDTO;
import com.example.exception.ApplicationException;
import com.example.model.Book;
import com.example.service.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
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

    @Test
    public void findBookStatusOK() throws Exception {
        Book book = buildBook("1234","Irobot","azimov");
        given(this.bookService.findOne("1234")).willReturn(book);
        this.mockMvc.perform(get("/books/1234")).andExpect(status().isOk());
    }
    @Test
    public void addBook() throws ApplicationException{
        Book book = buildBook("1234","Irobot","azimov");
        doNothing().when(bookService).addBook(book);

    }

    @Test
    public void getAllBooksSuccess()throws Exception{
        Book book = buildBook("1234","Azimov","Irobot");
        List<Book> bookList = Arrays.asList(book);
        given(this.bookService.getAllBooks()).willReturn(bookList);
        MvcResult result = this.mockMvc.perform(get("/books/all")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)).andReturn();
        List<BookDTO> dbBooks = objectMapper.readValue(result.getResponse().getContentAsString(),new TypeReference<List<BookDTO>>(){});
        Assert.assertEquals(book.getName(),dbBooks.get(0).getName());
        Assert.assertEquals(book.getAuthor(),dbBooks.get(0).getAuthor());

    }


    @Test
    public void findBookReturnOK() throws Exception {
        Book book = buildBook("1234","Irobot","azimov");
        given(this.bookService.findOne("1234")).willReturn(book);
        MvcResult response = this.mockMvc.perform(get("/books/1234")).andReturn();
        BookDTO returnedBook = (BookDTO)convertToObject(response.getResponse().getContentAsString(),new TypeReference<BookDTO>(){});
        Assert.assertEquals(book.getAuthor(),returnedBook.getAuthor());
        Assert.assertEquals(book.getName(),returnedBook.getName());
        Assert.assertEquals(book.getCode(),returnedBook.getCode());
    }

    @Test
    public void fromEntityToDto(){
        Book book = buildBook(null,"Irobot","azimov");
        BookDTO bookDTO = modelMapper.map(book,BookDTO.class);
        Assert.assertEquals(bookDTO.getAuthor(),book.getAuthor());
        Assert.assertEquals(bookDTO.getName(),book.getName());
        Assert.assertEquals(bookDTO.getCode(),book.getCode());
    }

    private Book buildBook(String code, String name,String author){
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setCode(code);
        return book;
    }

    Object convertToObject(final String content,TypeReference typeReference) throws IOException {
        return objectMapper.readValue(content,typeReference);
    }


}
