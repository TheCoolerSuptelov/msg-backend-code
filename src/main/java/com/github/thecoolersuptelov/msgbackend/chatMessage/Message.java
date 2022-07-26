package com.github.thecoolersuptelov.msgbackend.chatMessage;

import com.github.thecoolersuptelov.msgbackend.chat.Chat;
import com.github.thecoolersuptelov.msgbackend.chatUser.User;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@SqlResultSetMapping(name = "createMessage", columns = {@ColumnResult(name = "id", type = UUID.class), @ColumnResult(name = "chat", type = Chat.class), @ColumnResult(name = "author", type = User.class)})
@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    @org.hibernate.annotations.Type(type = "org.hibernate.type.PostgresUUIDType")
    private UUID id;

    @OneToOne(cascade = CascadeType.DETACH, orphanRemoval = true)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Lob
    @Column(name = "text")
    private String text;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp created_at;

    public Message() {
    }

    public Message(Chat chat, User author, String text) {
        this.chat = chat;
        this.author = author;
        this.text = text;
    }

    public Message(Chat chat, User author) {
        this.chat = chat;
        this.author = author;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public UUID getId() {
        return id;
    }


}