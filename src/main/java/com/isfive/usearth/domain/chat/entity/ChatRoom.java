package com.isfive.usearth.domain.chat.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatRoom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String roomName;

	@Column(nullable = false)
	private String description;

	private Integer memberCount;

	@OneToMany(mappedBy = "chatRoom")
	private List<ChatRoomAppend> chatRoomAppends = new ArrayList<>();

}
