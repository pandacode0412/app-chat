package com.project.chatapp.controller;

import com.project.chatapp.exception.ChatException;
import com.project.chatapp.exception.UserException;
import com.project.chatapp.modal.Chat;
import com.project.chatapp.modal.User;
import com.project.chatapp.request.GroupChatRequest;
import com.project.chatapp.request.SingleChatReques;
import com.project.chatapp.response.ApiResponse;
import com.project.chatapp.service.ChatService;
import com.project.chatapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chats")
public class ChatController {
    private ChatService chatService;
    private UserService userService;

    public ChatController(ChatService chatService , UserService userService) {
        this.chatService= chatService;
        this.userService = userService;
    }

    @PostMapping("/single")
    public ResponseEntity<Chat> createChatHandler(@RequestBody SingleChatReques singleChatReques, @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserProfile(jwt) ;
        Chat chat = chatService.createChat(reqUser , singleChatReques.getUserId());

        return new ResponseEntity<Chat>(HttpStatus.OK);

    }

    @PostMapping("/group")
    public ResponseEntity<Chat> createGroupHandler(@RequestBody GroupChatRequest req, @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserProfile(jwt) ;
        Chat chat = chatService.createGroup(req , reqUser);

        return new ResponseEntity<Chat>(HttpStatus.OK);

    }

    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> findChatByIdHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws Exception , ChatException {



        Chat chat = chatService.findChatById(chatId);

        return new ResponseEntity<Chat>(HttpStatus.OK);

    }

    @GetMapping("/user")
    public ResponseEntity<List<Chat>>  findAllChatByUserHandler( @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser = userService.findUserProfile(jwt) ;
        List<Chat> chats = chatService.findAllChatByUserId(reqUser.getId());

        return new ResponseEntity<List<Chat>>(HttpStatus.OK);

    }


    @PutMapping("/{chatId}/add/{userId}")
    public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable Integer chatId,@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception , ChatException {

        User reqUser = userService.findUserProfile(jwt) ;

        Chat chat = chatService.addUserToGroup(userId,chatId , reqUser);

        return new ResponseEntity<>(chat ,HttpStatus.OK);

    }

    @PutMapping("/{chatId}/remove/{userId}")
    public ResponseEntity<Chat> removeUserToGroupHandler(@PathVariable Integer chatId,@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception , ChatException {

        User reqUser = userService.findUserProfile(jwt) ;

        Chat chat = chatService.removeFromGroup(userId,chatId , reqUser);

        return new ResponseEntity<>(chat ,HttpStatus.OK);

    }

    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<ApiResponse> deleteChatHandler(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt) throws Exception , ChatException {

        User reqUser = userService.findUserProfile(jwt) ;

        chatService.deleteChat(chatId , reqUser.getId());

        ApiResponse res = new ApiResponse("chat is delete successfully", true);

        return new ResponseEntity<>(res ,HttpStatus.OK);

    }

}
