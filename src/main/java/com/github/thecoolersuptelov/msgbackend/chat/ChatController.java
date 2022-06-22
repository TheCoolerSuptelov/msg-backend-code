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

    @PostMapping(value = "add")
    public ResponseEntity<ChatDto> createChatWithUsers(@RequestBody ChatDto chatFromRequest){
        if (chatService.getChatRepository()
                .findByNameEqualsIgnoreCase(
                        chatFromRequest.getName()
                )
                .isPresent()) {
            chatFromRequest.setErrorDetails("Chat with that name already exist. Please, change name and try again.");
            return new ResponseEntity<>(chatFromRequest, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(chatService.addNewChat(chatFromRequest),
                HttpStatus.CREATED
        );
    }
}
