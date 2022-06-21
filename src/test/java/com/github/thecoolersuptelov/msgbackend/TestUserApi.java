package com.github.thecoolersuptelov.msgbackend;

import com.github.thecoolersuptelov.msgbackend.chatUser.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class TestUserApi {

//    @Autowired
//    public UserService userService;
    @Autowired
    private final WebApplicationContext webAppContext;
    public MockMvc mockMvc;


    public TestUserApi(
//            UserService userService,
                       @Autowired WebApplicationContext webAppContext){
//        this.userService = userService;
        this.webAppContext = webAppContext;
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    @Order(0)
    public void userCreationTest() throws Exception {
        var responseUserCreation = mockMvc.perform(
                post("/users/add")
                        .content("{\"username\": \"user_1\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertEquals(201,responseUserCreation.getStatus());
    }

    @Test
    @Order(1)
    public void shouldRaiseErrorDuplicateUsernameCreation() throws Exception {
        var responseUserCreation = mockMvc.perform(
                        post("/users/add")
                                .content("{\"username\": \"user_1\"}")
                                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        assertEquals(422,responseUserCreation.getStatus());
    }
}
