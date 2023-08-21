package com.isfive.usearth.domain.chat.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoomAppend is a Querydsl query type for ChatRoomAppend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoomAppend extends EntityPathBase<ChatRoomAppend> {

    private static final long serialVersionUID = 1649573733L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoomAppend chatRoomAppend = new QChatRoomAppend("chatRoomAppend");

    public final ListPath<ChatMessage, QChatMessage> chatMessages = this.<ChatMessage, QChatMessage>createList("chatMessages", ChatMessage.class, QChatMessage.class, PathInits.DIRECT2);

    public final QChatRoom chatRoom;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.isfive.usearth.domain.member.entity.QMember member;

    public QChatRoomAppend(String variable) {
        this(ChatRoomAppend.class, forVariable(variable), INITS);
    }

    public QChatRoomAppend(Path<? extends ChatRoomAppend> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoomAppend(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoomAppend(PathMetadata metadata, PathInits inits) {
        this(ChatRoomAppend.class, metadata, inits);
    }

    public QChatRoomAppend(Class<? extends ChatRoomAppend> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QChatRoom(forProperty("chatRoom")) : null;
        this.member = inits.isInitialized("member") ? new com.isfive.usearth.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

