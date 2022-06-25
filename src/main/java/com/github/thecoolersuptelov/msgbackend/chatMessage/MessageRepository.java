package com.github.thecoolersuptelov.msgbackend.chatMessage;

import com.github.thecoolersuptelov.msgbackend.chat.Chat;
import com.github.thecoolersuptelov.msgbackend.chatUser.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    /*@Query(value = "Select null as id, chat_id as chat, users_id as author_id  from chat_users " +
            "where chat_id = :chatId " +
            "and users_id = :userId",
    nativeQuery = true)
    Optional<Message> findChatAndUserConnected(@Param("chatId") UUID chatId,
                                               @Param("userId")UUID userId);*/
  /*  @Query(value = "Select null as id, chat_id as chat, users_id as author_id  from chat_users " +
            "where chat_id = ?1 " +
            "and users_id = ?2",
            nativeQuery = true)
    Optional<Message> findChatAndUserConnected(UUID chatId,
                                               UUID userId);

    @Query(value = "Select null as id, chat_id as chat, users_id as author_id  from chat_users " +
            "where chat_id = :chatId " +
            "and users_id = :userId",
            nativeQuery = true)
    List<Object[]> findAuthorAndChat(@Param("chatId") UUID chatId,
                                     @Param("userId")UUID userId);*/

}