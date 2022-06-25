package com.github.thecoolersuptelov.msgbackend.chatUser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "add")
    public ResponseEntity<String> createUser(@RequestBody UserDto userFromRequest) {
        if (userService.isUserExist(userFromRequest.getUsername())) {
            return new ResponseEntity<>("User with this username already exist."
                    + " Please, change username and try again.", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(userService.addNewUser(userFromRequest).getId().toString(),
                HttpStatus.CREATED
        );
    }

}
