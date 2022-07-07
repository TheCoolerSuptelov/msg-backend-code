package com.github.thecoolersuptelov.msgbackend.chatMessage;

import com.github.thecoolersuptelov.msgbackend.chat.Chat;
import com.github.thecoolersuptelov.msgbackend.chatUser.User;

import java.util.UUID;

public interface MessageView {
    Chat getChatId();
    User getAuthorId();
}
