package com.github.thecoolersuptelov.msgbackend.chatMessage;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    // TODO
    // change columns name, delete underscores
    @Query(nativeQuery = true, value = "select id, chat_id, author_id, text, created_at from message " +
            "where chat_id = :chat_id ")
    @Transactional(readOnly = true)
    List<Message> findByChat_IdEqualsOrderByCreated_atAsc(@Param("chat_id") UUID id);

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