package com.github.thecoolersuptelov.msgbackend.chatMessage;

import com.github.thecoolersuptelov.msgbackend.chat.ChatService;
import com.github.thecoolersuptelov.msgbackend.chatUser.User;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    public MessageDto createMessage(UUID author,UUID chat, String textMessage){
        //var authorUser = userService.getUserById(author);
        var messageWithChatUser = messageRepository.findChatAndUserConnected(chat, author);

        return null;
    }
}
