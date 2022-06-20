package com.github.thecoolersuptelov.msgbackend.chatUser;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userFromRequest){
        if (userService.getUserRepository().findByUsernameEqualsIgnoreCase(userFromRequest.getUsername()).isPresent()){
            userFromRequest.setErrorDetails("User with this username already exist. Please, change username and try again.");
            return new ResponseEntity<UserDto>(userFromRequest, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<UserDto>(new UserDto(userService.addNewUser(userFromRequest)),
                HttpStatus.CREATED
        );
    }

}
