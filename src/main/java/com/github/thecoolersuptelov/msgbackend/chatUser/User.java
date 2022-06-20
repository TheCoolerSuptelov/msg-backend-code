package com.github.thecoolersuptelov.msgbackend.chatUser;

import com.github.thecoolersuptelov.msgbackend.chat.Chat;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp created_at;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.DETACH)
    private Set<Chat> chats = new LinkedHashSet<>();

    public User() {
    }

    public Set<Chat> getChats() {
        return chats;
    }
    public User(UserDto userDto){

    }
    public void setChats(Set<Chat> chats) {
        this.chats = chats;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}