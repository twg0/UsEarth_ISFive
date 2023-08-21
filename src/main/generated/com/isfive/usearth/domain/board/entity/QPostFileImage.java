package com.isfive.usearth.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostFileImage is a Querydsl query type for PostFileImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostFileImage extends EntityPathBase<PostFileImage> {

    private static final long serialVersionUID = 1102476863L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostFileImage postFileImage = new QPostFileImage("postFileImage");

    public final com.isfive.usearth.domain.common.QFileImage fileImage;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPost post;

    public QPostFileImage(String variable) {
        this(PostFileImage.class, forVariable(variable), INITS);
    }

    public QPostFileImage(Path<? extends PostFileImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostFileImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostFileImage(PathMetadata metadata, PathInits inits) {
        this(PostFileImage.class, metadata, inits);
    }

    public QPostFileImage(Class<? extends PostFileImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fileImage = inits.isInitialized("fileImage") ? new com.isfive.usearth.domain.common.QFileImage(forProperty("fileImage")) : null;
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

