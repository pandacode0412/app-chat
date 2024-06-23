package com.project.chatapp.controller;

import com.project.chatapp.exception.UserException;
import com.project.chatapp.modal.User;
import com.project.chatapp.request.UpdateUserRequest;
import com.project.chatapp.response.ApiResponse;
import com.project.chatapp.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) throws UserException {

        User user = userService.findUserProfile(token);

        return new ResponseEntity<User>(user , HttpStatus.ACCEPTED);


    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String q ) {

        List<User> users = userService.searchUser(q);

        return new ResponseEntity<List<User>>(users , HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse>updateUserHandler(@RequestBody UpdateUserRequest req , @RequestHeader("Authorization") String token) throws UserException, ExecutionControl.UserException {

        User user = userService.findUserProfile(token);
        userService.updateUser(user.getId() , req);

        ApiResponse res = new ApiResponse("user updated successfully " , true);

        return  new ResponseEntity<ApiResponse>(res , HttpStatus.ACCEPTED);


    }



}
