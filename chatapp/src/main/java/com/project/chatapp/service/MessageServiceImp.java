package com.project.chatapp.service;

import com.project.chatapp.exception.ChatException;
import com.project.chatapp.exception.MessageException;
import com.project.chatapp.exception.UserException;
import com.project.chatapp.modal.Chat;
import com.project.chatapp.modal.Message;
import com.project.chatapp.modal.User;
import com.project.chatapp.repository.MessageRepository;
import com.project.chatapp.request.SendmessageReques;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImp implements MessageService{

    private MessageRepository messageRepository;
    private UserService userService;
    private ChatService chatService;

    public MessageServiceImp(MessageRepository messageRepository, UserService userService, ChatService chatService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public Message sendMesage(SendmessageReques req) throws UserException, ChatException {

        User user = userService.findUserById(req.getUserId());
        Chat chat = chatService.findChatById(req.getChatId());

        Message message = new Message();
        message.setChat(chat);
        message.setUser(user);
        message.setContent(req.getContent());
        message.setTimeStamp(LocalDateTime.now());


        return message;
    }

    @Override
    public List<Message> getChatsMessages(Integer chatId , User reqUser) throws ChatException, UserException {
        Chat chat = chatService.findChatById(chatId);

        if(!chat.getUsers().contains(reqUser)) {
            throw  new UserException("you are not releted to this chat" + chat.getId());

        }
        List<Message> messages = messageRepository.findByChatId(chat.getId());

        return messages;
    }

    @Override
    public Message findMessageById(Integer messageId) throws MessageException {
       Optional<Message> opt = messageRepository.findById(messageId);

       if (opt.isPresent()) {
           return opt.get();
       }

        throw  new MessageException("message not found with id" + messageId);
    }

    @Override
    public void deleteMessage(Integer messageId , User reqUser) throws MessageException, UserException {

        Message message = findMessageById(messageId);
        if (message.getUser().getId().equals(reqUser.getId())) {
            messageRepository.deleteById(messageId);
        }
        throw new UserException("you cạn delete another user`s message"+ reqUser.getFull_name());
    }
}
