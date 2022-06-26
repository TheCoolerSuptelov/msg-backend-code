package com.github.thecoolersuptelov.msgbackend.chat;

import com.github.thecoolersuptelov.msgbackend.chatUser.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chats/")
public class ChatController {
    @Autowired
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "addByUsername")
    public ResponseEntity<String> createChatWithUsersByUsername(@RequestBody ChatDto chatFromRequest) {
        return createChatWithUsers(chatFromRequest, "username");
    }
    @PostMapping(value = "add")
    public ResponseEntity<String> createChatWithUsers(@RequestBody ChatDto chatFromRequest, String findBy) {
        // Убрать кишки чат сервис. Сделать 1 функции внутри сервиса.
        if (chatService.isUserInAChat(chatFromRequest)) {
            return new ResponseEntity<>("Chat with that name already exist. Please, change name and try again.",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        String processedChat;
        if (findBy != null && !findBy.isEmpty()){
           processedChat = chatService.addNewChat(chatFromRequest, findBy);
        }else {
            processedChat = chatService.addNewChat(chatFromRequest, "Id");
        }
        return new ResponseEntity<>(processedChat, HttpStatus.CREATED);
    }


    @PostMapping(value = "get")
    public ResponseEntity<List<ChatDto>> getAllUserChats(@RequestBody UserDto userDto,
                                                         @RequestParam(defaultValue = "0") Integer pageNo,
                                                         @RequestParam(defaultValue = "50") Integer pageSize) {
        List<ChatDto> chats = chatService.getAllChatsByUser(userDto.getId(), pageNo,pageSize);
        return new ResponseEntity<>(chats, HttpStatus.CREATED);
    }
}
