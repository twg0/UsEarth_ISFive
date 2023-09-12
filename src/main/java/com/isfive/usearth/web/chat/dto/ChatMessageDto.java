package com.isfive.usearth.web.chat.dto;

import com.isfive.usearth.domain.chat.entity.ChatMessage;
import com.isfive.usearth.domain.chat.enums.MessageType;
import com.isfive.usearth.domain.common.BaseEntity;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class ChatMessageDto implements Serializable  {


    private String roomId;
    private String nickname;
    private String message;
    private LocalDateTime time;
    private MessageType type;

    public ChatMessage toEntity() {


        return ChatMessage.builder()
                .nickname(this.nickname)
                .message(this.message)
                .build();
    }
}