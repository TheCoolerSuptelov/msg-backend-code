package com.github.thecoolersuptelov.msgbackend.chatMessage;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class MessageDto implements Serializable {
    UUID chat;
    UUID author;
    String text;
    private UUID id;

    public MessageDto(UUID chat, UUID author, String text) {
        this.chat = chat;
        this.author = author;
        this.text = text;
    }

    public MessageDto(Message message) {
        this.id = message.getId();
        this.author = message.getAuthor().getId();
        this.text = message.getText();
        this.chat = message.getChat().getId();
    }

    public UUID getChat() {
        return chat;
    }

    public void setChat(UUID chat) {
        this.chat = chat;
    }

    public UUID getAuthor() {
        return author;
    }

    public void setAuthor(UUID author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDto entity = (MessageDto) o;
        return Objects.equals(this.chat, entity.chat) && Objects.equals(this.author, entity.author) && Objects.equals(this.text, entity.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chat, author, text);
    }

    @Override
    public String toString() {
        return "" + "id=" + id + ' ';
    }
}
