package com.github.thecoolersuptelov.msgbackend.chatMessage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
}