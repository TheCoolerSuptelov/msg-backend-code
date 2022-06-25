package com.github.thecoolersuptelov.msgbackend.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    Optional<Chat> findByNameEqualsIgnoreCase(String name);

    Chat findByIdEqualsAndUsers_IdEquals(UUID chatId, UUID userId);

    boolean existsByUsers_IdEqualsAndIdEquals(UUID userId, UUID chatId);

}