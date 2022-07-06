package com.github.thecoolersuptelov.msgbackend.chat;

import com.github.thecoolersuptelov.msgbackend.chatUser.UserDto;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserSearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats/")
public class ChatController {
    @Autowired
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(value = "addByUsername")
    public ResponseEntity<String> createChatWithUsersByUsername(@RequestBody ChatDto chatFromRequest) throws ChatCreationException {
        return createChatWithUsers(chatFromRequest, UserSearchStrategy.BY_ID);
    }

    @PostMapping(value = "add")
    public ResponseEntity<String> createChatWithUsers(@RequestBody ChatDto chatFromRequest, UserSearchStrategy findBy) throws ChatCreationException {
        try {
            return chatService.addNewChat(chatFromRequest, findBy);
        } catch (ChatCreationException e) {
            return new ResponseEntity<>("Cannot create chat." + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping(value = "get")
    public ResponseEntity<List<ChatDto>> getAllUserChats(@RequestBody UserDto userDto, @RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "50") Integer pageSize) {
        List<ChatDto> chats = chatService.getAllChatsByUser(userDto.getId(), pageNo, pageSize);
        return new ResponseEntity<>(chats, HttpStatus.CREATED);
    }
}
