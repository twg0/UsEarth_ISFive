package com.isfive.usearth.domain.chat.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
