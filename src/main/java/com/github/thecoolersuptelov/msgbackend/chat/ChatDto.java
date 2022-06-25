package com.github.thecoolersuptelov.msgbackend.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.thecoolersuptelov.msgbackend.chatUser.User;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class ChatDto implements Serializable {
    @JsonProperty("chat")
    private UUID id;

    private String name;

    private Set<String> users;

    public ChatDto(String name, Set<String> users) {
        this.name = name;
        this.users = users;
    }

    public ChatDto(Chat chat) {
        this.id = chat.getId();
        this.name = chat.getName();
        this.users = chat.getUsers().stream().map(User::getUsername).collect(Collectors.toSet());
    }

    public ChatDto() {

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatDto entity = (ChatDto) o;
        return Objects.equals(this.name, entity.name) && Objects.equals(this.users, entity.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + "name = " + name + ", " + "users = " + users + ")";
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }
}
