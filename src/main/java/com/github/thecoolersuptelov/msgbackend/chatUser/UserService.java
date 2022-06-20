package com.github.thecoolersuptelov.msgbackend.chatUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User addNewUser(UserDto userToCreation) {
        var  createdUser = new User(userToCreation);
        userRepository.save(createdUser);
        return createdUser.getId().toString();
    }

}
