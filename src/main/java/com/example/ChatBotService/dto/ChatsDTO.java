package com.example.ChatBotService.dto;

import com.example.ChatBotService.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatsDTO {
   private Long id;
   private String username;
    private String chatsRequest;
   private String chatsResponse;
   private String domain;
   private ChatType chatType;
}
