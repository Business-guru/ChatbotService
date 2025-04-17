package com.example.ChatBotService.repository;

import com.example.ChatBotService.dto.ChatsDTO;
import com.example.ChatBotService.entity.Chats;
import com.example.ChatBotService.enums.ChatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatsRepository extends JpaRepository<Chats,Long> {
    @Query("SELECT c FROM Chats c WHERE c.userId = :userId AND c.chatType = :chatType ORDER BY c.createdAt ASC")
    List<Chats> findAllByUserIdAndChatTypeOrderByCreatedAtAsc(
            @Param("userId") String userId,
            @Param("chatType") ChatType chatType
    );

}
