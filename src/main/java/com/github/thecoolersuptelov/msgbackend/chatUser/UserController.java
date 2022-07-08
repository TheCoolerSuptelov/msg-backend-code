package com.github.thecoolersuptelov.msgbackend.chatUser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "add")
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody UserDto userFromRequest) throws UserException{

       return userService.addNewUser(userFromRequest).getId().toString();
    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlingException(RuntimeException e){
        return e.getMessage();
    }

}
