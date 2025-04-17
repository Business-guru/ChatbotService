package com.example.ChatBotService.entity;

import com.example.ChatBotService.enums.ChatType;
import com.example.ChatBotService.commons.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="chats")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chats extends BaseEntity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @Column
   private String userId;

   @Column
   private String chatsRequest;

   @Column
    private String chatsResponse;

   @Column
   private String domain;

   @Column
   @Enumerated(EnumType.STRING)
   private ChatType chatType;
}
