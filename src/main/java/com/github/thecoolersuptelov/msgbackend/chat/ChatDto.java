package com.github.thecoolersuptelov.msgbackend.chat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.thecoolersuptelov.msgbackend.chatUser.User;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ChatDto implements Serializable {
    private String name;
    // TODO
    // Придумать как обойти исключение и отдавать только ID для ответа при создании чата
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Set<String> users;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String errorDetails;

    public ChatDto(String name, Set<String> users) {
        this.name = name;
        this.users = users;
    }

    public ChatDto(Chat chat){
        this.name = chat.getName();
        this.users = chat.getUsers().stream().map(User::getUsername).collect(Collectors.toSet());
    }

    public ChatDto() {

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
