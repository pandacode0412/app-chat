package com.project.chatapp.service;

import com.project.chatapp.exception.UserException;
import com.project.chatapp.modal.User;
import com.project.chatapp.request.UpdateUserRequest;
import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface UserService {
    public User findUserById(Integer id) throws UserException;
    public User findUserProfile(String jwt) throws UserException;
    public User updateUser(Integer userId, UpdateUserRequest req) throws ExecutionControl.UserException, UserException;
    public List<User> searchUser(String query);
}
