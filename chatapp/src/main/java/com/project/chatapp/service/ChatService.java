package com.project.chatapp.service;

import com.project.chatapp.exception.ChatException;
import com.project.chatapp.exception.UserException;
import com.project.chatapp.modal.Chat;
import com.project.chatapp.modal.User;
import com.project.chatapp.request.GroupChatRequest;

import java.util.List;

public interface ChatService {

    public Chat createChat(User reqUser , Integer userId2) throws  Exception;

    public Chat findChatById(Integer chatId) throws ChatException;

    public List<Chat> findAllChatByUserId(Integer userId) throws UserException;

    public Chat createGroup (GroupChatRequest req , User reqUser) throws UserException;

    public  Chat addUserToGroup(Integer userId, Integer chatId , User reqUser) throws UserException , ChatException;

    public  Chat renameGroup(Integer chatId, String groupName , User reqUser) throws ChatException , UserException;

    public Chat removeFromGroup(Integer chatId , Integer userId , User reqUser) throws  UserException , ChatException;

    public  void deleteChat(Integer chatId , Integer userId) throws ChatException , UserException;



}
