package com.github.thecoolersuptelov.msgbackend;

import org.junit.jupiter.api.MethodOrderer;
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

    void should_createUser_createChat_sendMessagesFromUserInChat() throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        var responseUserCreation = mockMvc.perform(post("/users/add").content("{\"username\": \"user_" + currentTimeMillis + "\"}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        var chatCreation = mockMvc.perform(post("/chats/add").content("{\"name\": \"chat_" + currentTimeMillis + "\", \"users\": [\"00000000-b91c-4ef3-9e78-51c35c3b65da\", \"00000000-5f19-40a5-8109-f3cadee4519b\"]}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        var messageCreated = mockMvc.perform(post("/messages/add").content("{\"chat\": \"00000000-aab4-4402-af57-bbce2b05fb63\", \"author\":\"00000000-b91c-4ef3-9e78-51c35c3b65da\", \"text\":\"Sometextdata\"}").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

    }
}
