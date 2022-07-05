package com.github.thecoolersuptelov.msgbackend;



import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    private final WebApplicationContext webAppContext;
    public MockMvc mockMvc;
    public UserControllerTest(WebApplicationContext webAppContext){

        this.webAppContext = webAppContext;
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    @Order(1)
    public void should_ReturnCreated_Then_CreateUser() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        var responseUserCreation = mockMvc.perform(
                post("/users/add")
                        .content("{\"username\": \"user_"+ currentTimeMillis +"\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertEquals(201,responseUserCreation.getStatus());
    }

    @Test
    public void should_ReturnBadRequest_Then_DuplicateUsernameCreation() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        var responseUserCreation = mockMvc.perform(
                        post("/users/add")
                                .content("{\"username\": \"user_"+ currentTimeMillis +"\"}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertEquals(201,responseUserCreation.getStatus());

        var doubleResponseUserCreation = mockMvc.perform(
                        post("/users/add")
                                .content("{\"username\": \"user_"+currentTimeMillis+"\"}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertEquals(400,doubleResponseUserCreation.getStatus());
    }
}
