package com.example.ChatBotService.controller;

import com.example.ChatBotService.dto.ChatsDTO;
import com.example.ChatBotService.dto.QueryResponse;
import com.example.ChatBotService.enums.ChatType;
import com.example.ChatBotService.service.ChatsService;
import com.example.ChatBotService.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("service/api/v1/")
public class ChatsController {

    @Autowired
    private ChatsService chatsService;
    @Autowired
    private JwtUtils jwtUtils;
  @PostMapping("chatService/getResponse")
  public ResponseEntity<QueryResponse> getChatResponse(@RequestHeader("Authorization") String token, @RequestBody ChatsDTO chatsDTO)
  {
      String parseToken=token.substring(7);
      String username=jwtUtils.extractUserName(parseToken);

      return ResponseEntity.ok(chatsService.getChatResponse(chatsDTO,username));
  }
    @GetMapping("chatService/allChats/{chatType}")
    public ResponseEntity<List<ChatsDTO>> getAllChats(@RequestHeader("Authorization") String token, @PathVariable ChatType chatType)
    {
        String parseToken=token.substring(7);
        String username=jwtUtils.extractUserName(parseToken);
        return ResponseEntity.ok(chatsService.getAllChats(username,chatType));
    }
}
