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
class MessageControllerTest {
    public MockMvc mockMvc;

    public MessageControllerTest(WebApplicationContext webAppContext) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    @Order(1)
    void should_ReturnCreated_then_createdMessage() throws Exception {
        var chatCreation = mockMvc.perform(post("/messages/add")
                .content("{\"chat\": \"00000000-aab4-4402-af57-bbce2b05fb63\", \"author\":\"00000000-b91c-4ef3-9e78-51c35c3b65da\", \"text\":\"Sometextdata\"}")
                .contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        assertEquals(201, chatCreation.getStatus());
    }

}

