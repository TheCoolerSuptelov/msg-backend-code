package com.github.thecoolersuptelov.msgbackend.chatMessage;

import com.github.thecoolersuptelov.msgbackend.chat.ChatService;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @Autowired
    private MessageRepository messageRepository;

    public MessageDto createMessage(UUID author, UUID chat, String textMessage) {
        if (!chatService.existUserInChat(chat, author)) {
            return null;
        }
        // TODO
        // 1 sql query instead of 2
        var userAuthor = userService.getUserById(author).get();
        var chatPersist = chatService.getChatRepository().findById(chat).get();

        Message message = new Message(chatPersist, userAuthor, textMessage);
        messageRepository.save(message);
        return new MessageDto(message);
    }

    public List<MessageDto> getAllMessagesFromChat(UUID chatId) {
        return messageRepository.findByChat_IdEqualsOrderByCreated_atAsc(chatId)
                .stream()
                .map(MessageDto::new)
                .collect(Collectors.toList());
    }
}
