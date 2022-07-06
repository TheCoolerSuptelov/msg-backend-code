package com.github.thecoolersuptelov.msgbackend.chatUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public User addNewUser(UserDto userToCreation) throws UserException {
        if (isUserExist(userToCreation.getUsername())) {
            throw new UserException("User with this username already exist."
                    + " Please, change username and try again.");
        }
        var createdUser = new User(userToCreation);
        userRepository.save(createdUser);
        return createdUser;
    }

    public boolean isUserExist(String username) {
        return userRepository.existsByUsernameEquals(username);
    }

}
