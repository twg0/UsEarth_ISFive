package com.isfive.usearth.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoomAppend {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// private ChatRoom chatRoom;
	// private Member member;
	// private List<ChatMessage> chatMessages;
}
