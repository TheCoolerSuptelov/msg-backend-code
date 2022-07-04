package com.github.thecoolersuptelov.msgbackend.chatMessage;

import com.github.thecoolersuptelov.msgbackend.chat.ChatRepository;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;

    public String createMessage(UUID author, UUID chat, String textMessage) {

        var userAuthor = userRepository.findById(author).get();
        var chatPersist = chatRepository.findById(chat).get();

        Message message = new Message(chatPersist, userAuthor, textMessage);
        messageRepository.save(message);
        return message.getId().toString();
    }

    public List<MessageDto> getAllMessagesFromChat(UUID chatId) {
        return messageRepository.findByChat_IdEqualsOrderByCreated_atAsc(chatId).stream().map(MessageDto::new).collect(Collectors.toList());
    }
}
