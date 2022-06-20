package com.github.thecoolersuptelov.msgbackend.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}