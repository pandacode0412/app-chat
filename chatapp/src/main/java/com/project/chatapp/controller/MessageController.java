package com.project.chatapp.controller;

import com.project.chatapp.exception.ChatException;
import com.project.chatapp.exception.MessageException;
import com.project.chatapp.exception.UserException;
import com.project.chatapp.modal.Message;
import com.project.chatapp.modal.User;
import com.project.chatapp.request.SendmessageReques;
import com.project.chatapp.response.ApiResponse;
import com.project.chatapp.service.MessageService;
import com.project.chatapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private MessageService messageService;
    private UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/create")
    private ResponseEntity<Message> sendMessageHandler(@RequestBody SendmessageReques req , @RequestHeader("Authorization")String jwt) throws UserException, ChatException {
        User user = userService.findUserProfile(jwt);
        req.setUserId(user.getId());

        Message message = messageService.sendMesage(req);

        return new ResponseEntity<Message>(message, HttpStatus.OK);

    }

    @GetMapping("/chat/{chatId}")
    private ResponseEntity<List<Message>> getChatMessagesHandler( @PathVariable Integer chatId , @RequestHeader("Authorization")String jwt) throws UserException, ChatException {
        User user = userService.findUserProfile(jwt);

        List<Message> messages= messageService.getChatsMessages(chatId , user);

        return new ResponseEntity<>(messages, HttpStatus.OK);

    }

    @DeleteMapping("/{messageId}")
    private ResponseEntity<ApiResponse> deleteMessagesHandler(@PathVariable Integer messageId , @RequestHeader("Authorization")String jwt) throws UserException, ChatException, MessageException {
        User user = userService.findUserProfile(jwt);

        messageService.deleteMessage(messageId , user);
        ApiResponse res = new ApiResponse("message deleted successfully" , false);

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

}
