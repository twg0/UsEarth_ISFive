package com.isfive.usearth.web.chat.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.isfive.usearth.domain.chat.dto.ChatRoomResponse;
import com.isfive.usearth.domain.chat.service.ChatService;
import com.isfive.usearth.web.chat.dto.ChatMessageDto;
import com.isfive.usearth.web.chat.dto.ChatRoomDto;
import com.isfive.usearth.web.chat.dto.ChatRoomRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "http://localhost:3000")
@Slf4j
public class ChatRoomController {
    private final ChatService chatService;

    @GetMapping("/rooms")
    public List<ChatRoomResponse> getAllRoom(
    ) {
        return chatService.readAllChatRoom();
    }

    @GetMapping("/myRooms")
    public List<ChatRoomResponse> getMyRoom(){
        return chatService.readMyChatRoom();
    }


    @GetMapping("/myRoom/{roomId}")
    public List<ChatMessageDto> getChatMessage(
            @PathVariable("roomId") Long roomId
    ) throws JsonProcessingException {
        return chatService.readChatMessages(roomId);
    }

    @PostMapping("/room")
    public ChatRoomResponse createRoom( @RequestBody ChatRoomRequest request) {
        log.info("{}",request.toString());
        ChatRoomDto chatRoomDto = new ChatRoomDto(request);
        return chatService.createChatRoom(chatRoomDto);
    }

    @GetMapping("/room/{roomId}")
    public ChatRoomResponse roomInfo(@PathVariable("roomId") Long id) {
        return chatService.readChatRoom(id);
    }
}
