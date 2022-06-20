package com.github.thecoolersuptelov.msgbackend.chat;

import com.github.thecoolersuptelov.msgbackend.chatUser.UserDto;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class ChatDto implements Serializable {
    private final String name;
    private final Set<UserDto> users;

    public ChatDto(String name, Set<UserDto> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public Set<UserDto> getUsers() {
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
