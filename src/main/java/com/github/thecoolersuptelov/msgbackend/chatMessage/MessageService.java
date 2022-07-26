package com.github.thecoolersuptelov.msgbackend.chatMessage;

import com.github.thecoolersuptelov.msgbackend.chat.Chat;
import com.github.thecoolersuptelov.msgbackend.chat.ChatRepository;
import com.github.thecoolersuptelov.msgbackend.chatUser.User;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageRepository messageRepository;
    @PersistenceContext
    EntityManager entityManager;
    public boolean existUserInChat(UUID chatId, UUID userId) {
        return chatRepository.existsByUsers_IdEqualsAndIdEquals(userId, chatId);
    }

    public String createMessage(UUID author, UUID chat, String textMessage) throws MessageCreationException {
        if (!existUserInChat(chat, author)) {
            throw new MessageCreationException("User doesn't exist in that chat.");
        }

        //var userAuthor = userRepository.findById(author).get();
        //var chatPersist = chatRepository.findById(chat).get();
//        var xx = messageRepository.findAuthorIdAndChatId(author,chat);
        var emQuery=  entityManager.createQuery("Select c , u from Chat as c, User as u where c.id = :chatId and u.id= :authorId");
        emQuery.setParameter("chatId", author);
        emQuery.setParameter("authorId", author);
        List<Object[]> someMessage = emQuery.getResultList();
        if (someMessage.size() == 0){
            throw new MessageCreationException("Cannot find that user and chat id's");
        }
        Message message = new Message((Chat) someMessage.get(0)[0], (User) someMessage.get(0)[1], textMessage);
        messageRepository.save(message);
        return message.getId().toString();
    }

    public List<MessageDto> getAllMessagesFromChat(UUID chatId) {
        return messageRepository.findByChat_IdEqualsOrderByCreated_atAsc(chatId).stream().map(MessageDto::new).toList();
    }
}
