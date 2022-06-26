package com.github.thecoolersuptelov.msgbackend.chat;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    Optional<Chat> findByNameEqualsIgnoreCase(String name);

    Chat findByIdEqualsAndUsers_IdEquals(UUID chatId, UUID userId);

    boolean existsByUsers_IdEqualsAndIdEquals(UUID userId, UUID chatId);

    @Query(nativeQuery = true, value = "Select chat_users.chat_id as id, chat.name as name," + " chat.created_at as created_at, " + "max(COALESCE(message.created_at,to_timestamp(0))) from chat_users " + " inner join chat on chat.id = chat_users.chat_id " + "left join message on message.chat_id = chat_users.chat_id " + "where users_id = :userId " + "group by chat_users.chat_id,chat.name,chat.created_at " + "order by max(COALESCE(message.created_at,to_timestamp(0)))", countQuery = "Select Sum(1) from chat_users where chat_users.users_id = :userId")
    List<Chat> findAllChatsByUserSortedByLatestMessage(@Param("userId") UUID userId, PageRequest pageable);
}

