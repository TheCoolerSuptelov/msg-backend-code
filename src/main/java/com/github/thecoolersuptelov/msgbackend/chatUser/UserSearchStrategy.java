package com.github.thecoolersuptelov.msgbackend.chatUser;

public enum UserSearchStrategy {
    BY_ID("Id"), BY_USERNAME("UserName");

    private final String searchAttribute;
    private Object searchAlgo;

    UserSearchStrategy(String attribute) {
        searchAttribute = attribute;
    }

}
