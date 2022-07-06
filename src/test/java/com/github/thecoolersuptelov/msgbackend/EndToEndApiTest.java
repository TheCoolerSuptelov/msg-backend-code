package com.github.thecoolersuptelov.msgbackend;

import org.junit.jupiter.api.MethodOrderer;
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
public class EndToEndApiTest {
    public MockMvc mockMvc;

    public EndToEndApiTest(WebApplicationContext webAppContext) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }
    @Test
    void should_createUser_createChat_sendMessagesFromUserInChat() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        var responseUserCreation = mockMvc.perform(post("/users/add").content("{\"username\": \"user_" + currentTimeMillis + "\"}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(201, responseUserCreation.getStatus());
        String userId = responseUserCreation.getContentAsString();
        var chatCreation = mockMvc.perform(post("/chats/add").content("{\"name\": \"chat_" + currentTimeMillis + "\", \"users\": [\""+userId+"\"]}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(201, chatCreation.getStatus());
        String chatID = chatCreation.getContentAsString();
        var messageCreated = mockMvc.perform(post("/messages/add").content("{\"chat\": \""+chatID+"\", \"author\":\""+ userId+"\", \"text\":\"Sometextdata\"}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(201, messageCreated.getStatus());
    }
}
