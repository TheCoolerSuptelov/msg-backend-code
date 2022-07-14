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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChatControllerTest {
    public MockMvc mockMvc;

    public ChatControllerTest(WebApplicationContext webAppContext) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    @Order(1)
    void should_ReturnCreated_then_createdChat() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        var chatCreation = mockMvc.perform(post("/chats/add").content("{\"name\": \"chat_" + currentTimeMillis + "\", \"users\": [\"00000000-b91c-4ef3-9e78-51c35c3b65da\", \"00000000-5f19-40a5-8109-f3cadee4519b\"]}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(201, chatCreation.getStatus());
    }

    @Test
    void should_ReturnBadRequest_then_createdChatWithExistedName() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        var chatCreation = mockMvc.perform(post("/chats/add").content("{\"name\": \"chat_" + currentTimeMillis + "\", \"users\": [\"00000000-b91c-4ef3-9e78-51c35c3b65da\", \"00000000-5f19-40a5-8109-f3cadee4519b\"]}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        var chatCreationDublicate = mockMvc.perform(post("/chats/add").content("{\"name\": \"chat_" + currentTimeMillis + "\", \"users\": [[\"00000000-b91c-4ef3-9e78-51c35c3b65da\", \"00000000-5f19-40a5-8109-f3cadee4519b\"]}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(400, chatCreationDublicate.getStatus());

    }

    @Test
    void should_ReturnBadRequest_then_createdChatWithNonExistedUser() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        String invalidId = "90000000-5f19-40a5-8109-f3cadee4519b";
        var chatCreation = mockMvc.perform(post("/chats/add").content("{\"name\": \"chat_" + currentTimeMillis + "\", \"users\": [\"00000000-b91c-4ef3-9e78-51c35c3b65da\", \"" + invalidId+"\"]}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertEquals(400, chatCreation.getStatus());
        assertTrue(chatCreation.getContentAsString().contains(invalidId));
    }

}
