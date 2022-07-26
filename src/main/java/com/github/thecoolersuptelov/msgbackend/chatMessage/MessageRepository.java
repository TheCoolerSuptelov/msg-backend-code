package com.github.thecoolersuptelov.msgbackend.chatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query(nativeQuery = true, value = "select id, chat_id, author_id, text, created_at from message " + "where chat_id = :chat_id ")
    @Transactional(readOnly = true)
    List<Message> findByChat_IdEqualsOrderByCreated_atAsc(@Param("chat_id") UUID id);



//    @Query(value = "Select chat_id as chatId, users_id as AuthorId  from chat_users where chat_id = :chatId and users_id= :authorId"
//            ,nativeQuery = true)
//    MessageView findAuthorIdAndChatId(@Param("authorId") UUID authorId,@Param("chatId") UUID chatId);
//    @Query(value = "select new com.github.thecoolersuptelov.msgbackend.chatMessage.Message(chat_id, users_id) from Message right join chat_users on true inner join userApp as u on u.id = chat_users.users_id inner join chat as c on c.id = chat_users.chat_id where chat_id = :chatId and users_id= :authorId")
//    Message findAuthorIdAndChatId(@Param("authorId") UUID authorId,@Param("chatId") UUID chatId);

}