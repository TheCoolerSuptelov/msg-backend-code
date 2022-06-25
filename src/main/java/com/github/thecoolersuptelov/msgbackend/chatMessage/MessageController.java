package com.github.thecoolersuptelov.msgbackend.chatMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages/")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping(value = "add")
    public ResponseEntity<MessageDto> createChatWithUsersByUsername(@RequestBody MessageDto messageFromRequest) {
        MessageDto createdMessage = messageService.createMessage(messageFromRequest.author,
                messageFromRequest.chat,
                messageFromRequest.text
        );
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }
}
