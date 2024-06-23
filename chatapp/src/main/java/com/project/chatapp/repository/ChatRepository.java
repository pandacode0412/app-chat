package com.project.chatapp.repository;

import com.project.chatapp.modal.Chat;
import com.project.chatapp.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {


//    @Query("select c from Chat c join c.users u where u.id =:userId")
////    public List<Chat> findChatByUserid(@Param("userId") Integer userId);
////
////    @Query("select c from chat c where c.isGroup=false And :user Member of c.users And :reqUser Member of c.users")
////    public Chat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser")User reqUser);

    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id =:userId")
    public List<Chat> findChatByUserid(@Param("userId") Integer userId);

    @Query("SELECT c FROM Chat c WHERE c.isGroup = false AND :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    public Chat findSingleChatByUserIds(@Param("user") User user, @Param("reqUser") User reqUser);


}
