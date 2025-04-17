package com.example.ChatBotService.service;

import com.example.ChatBotService.dto.ChatsDTO;
import com.example.ChatBotService.dto.QueryRequest;
import com.example.ChatBotService.dto.QueryResponse;
import com.example.ChatBotService.entity.Chats;
import com.example.ChatBotService.enums.ChatType;
import com.example.ChatBotService.repository.ChatsRepository;
import com.example.ChatBotService.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChatsService {
    private final ChatsRepository chatsRepository;
    @Value("${chatBot_Api}")
    private String url;
    public String getChatResponse(ChatsDTO chatsDTO,String userId)
    {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            QueryRequest request= QueryRequest.builder().query(chatsDTO.getChatsRequest()).domain(chatsDTO.getDomain()).build();
            HttpEntity<QueryRequest> entity = new HttpEntity<>(request, headers);
            RestTemplate restTemplate=new RestTemplate();
            ResponseEntity<QueryResponse> responseEntity = restTemplate.postForEntity(url, entity, QueryResponse.class);
            if(responseEntity.getStatusCode().is2xxSuccessful()) {
                QueryResponse queryResponse=responseEntity.getBody();
                assert queryResponse != null;
                String response=queryResponse.getResponse();
                chatsDTO.setUserId(userId);
                chatsDTO.setChatsResponse(response);
                ModelMapper mapper = new ModelMapper();
                chatsRepository.save(mapper.map(chatsDTO, Chats.class));
                return responseEntity.getBody().toString();
            }
            else  throw  new RuntimeException();
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ChatsDTO> getAllChats(String userId, ChatType chatType)
    {
        ModelMapper modelMapper=new ModelMapper();
        List<Chats> chatsList=chatsRepository.findAllByUserIdAndChatTypeOrderByCreatedAtAsc(userId,chatType);
        return chatsList.stream()
                .map(user -> modelMapper.map(user,ChatsDTO.class))
                .toList();
    }


}
