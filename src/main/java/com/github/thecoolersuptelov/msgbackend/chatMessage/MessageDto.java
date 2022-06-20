package com.github.thecoolersuptelov.msgbackend.chatMessage;

import com.github.thecoolersuptelov.msgbackend.chat.ChatDto;
import com.github.thecoolersuptelov.msgbackend.chatUser.UserDto;

import java.io.Serializable;
import java.util.Objects;

public class MessageDto implements Serializable {
    private final ChatDto chat;
    private final UserDto author;
    private final String text;

    public MessageDto(ChatDto chat, UserDto author, String text) {
        this.chat = chat;
        this.author = author;
        this.text = text;
    }

    public ChatDto getChat() {
        return chat;
    }

    public UserDto getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDto entity = (MessageDto) o;
        return Objects.equals(this.chat, entity.chat) &&
                Objects.equals(this.author, entity.author) &&
                Objects.equals(this.text, entity.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chat, author, text);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "chat = " + chat + ", " +
                "author = " + author + ", " +
                "text = " + text + ")";
    }
}
