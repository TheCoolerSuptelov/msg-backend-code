package com.github.thecoolersuptelov.msgbackend.chat;

import com.github.thecoolersuptelov.msgbackend.chatUser.User;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "chat_users",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> users = new LinkedHashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}