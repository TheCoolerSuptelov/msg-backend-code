package com.github.thecoolersuptelov.msgbackend.chatUser;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {
    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    private String errorDetails;

    public UserDto(String username, String errorDetails) {
        this.username = username;
        this.errorDetails = errorDetails;
    }
    public UserDto(User user){
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto entity = (UserDto) o;
        return Objects.equals(this.username, entity.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        String errorDetailsToString = "";
        if (!this.getErrorDetails().isEmpty()){
            errorDetailsToString = ", errorDetails" + this.getErrorDetails();
        }
        // TODO
        // ternary operator
        return getClass().getSimpleName() + "(" +
                "username = " + username + errorDetailsToString +")";
    }
}
