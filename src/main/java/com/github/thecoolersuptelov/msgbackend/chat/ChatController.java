package com.github.thecoolersuptelov.msgbackend.chat;

import com.github.thecoolersuptelov.msgbackend.chatUser.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chats/")
public class ChatController {
    @Autowired
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "addByUsername")
    public ResponseEntity<ChatDto> createChatWithUsersByUsername(@RequestBody ChatDto chatFromRequest){
        // TODO
        // Убрать кишки чат сервис. Сделать 1 функции внутри сервиса.
        if (chatService.getChatRepository()
                .findByNameEqualsIgnoreCase(
                        chatFromRequest.getName()
                )
                .isPresent()) {
            chatFromRequest.setErrorDetails("Chat with that name already exist. Please, change name and try again.");
            return new ResponseEntity<>(chatFromRequest, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var processedChat = chatService.addNewChat(chatFromRequest, "username");

        if (processedChat.getErrorDetails() == null || processedChat.getErrorDetails().isEmpty()){
            return new ResponseEntity<>(processedChat, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(processedChat,HttpStatus.CREATED);
    }

    @PostMapping(value = "add")
    public ResponseEntity<ChatDto> createChatWithUsers(@RequestBody ChatDto chatFromRequest){
        // Убрать кишки чат сервис. Сделать 1 функции внутри сервиса.
        if (chatService.getChatRepository()
                .findByNameEqualsIgnoreCase(
                        chatFromRequest.getName()
                )
                .isPresent()) {
            chatFromRequest.setErrorDetails("Chat with that name already exist. Please, change name and try again.");
            return new ResponseEntity<>(chatFromRequest, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var processedChat = chatService.addNewChat(chatFromRequest, "Id");

        if (processedChat.getErrorDetails() == null || processedChat.getErrorDetails().isEmpty()){
            return new ResponseEntity<>(processedChat, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(processedChat,HttpStatus.CREATED);
    }


}
