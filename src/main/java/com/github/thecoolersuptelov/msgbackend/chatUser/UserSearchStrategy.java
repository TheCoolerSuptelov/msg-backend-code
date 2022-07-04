package com.github.thecoolersuptelov.msgbackend.chatUser;

public enum UserSearchStrategy {
    ByID("Id"), ByUsername("UserName");

    private final String searchAttribute;
    private Object searchAlgo;

    UserSearchStrategy(String attribute) {
        searchAttribute = attribute;
    }

}
