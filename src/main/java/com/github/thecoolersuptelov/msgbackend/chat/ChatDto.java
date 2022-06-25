package com.github.thecoolersuptelov.msgbackend.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<String> users;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String errorDetails;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatDto entity = (ChatDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.users, entity.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "users = " + users + ")";
    }
}
