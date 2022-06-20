package com.github.thecoolersuptelov.msgbackend.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats/")
public class ChatController {
    @Autowired
    private ChatService chatService;
}
