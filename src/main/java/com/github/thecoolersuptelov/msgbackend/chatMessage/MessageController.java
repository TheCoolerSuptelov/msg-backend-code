package com.github.thecoolersuptelov.msgbackend.chatMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages/")
public class MessageController {
    @Autowired
    private MessageService messageService;
}
