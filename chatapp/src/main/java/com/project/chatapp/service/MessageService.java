package com.project.chatapp.service;

import com.project.chatapp.exception.ChatException;
import com.project.chatapp.exception.MessageException;
import com.project.chatapp.exception.UserException;
import com.project.chatapp.modal.Message;
import com.project.chatapp.modal.User;
import com.project.chatapp.request.SendmessageReques;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface MessageService {
    public Message sendMesage(SendmessageReques req) throws UserException , ChatException;

    public List<Message> getChatsMessages(Integer chatId , User reqUser) throws ChatException, UserException;

    public Message findMessageById(Integer messageId) throws MessageException;

    public void deleteMessage(Integer messageId , User reqUser) throws MessageException, UserException;




}
