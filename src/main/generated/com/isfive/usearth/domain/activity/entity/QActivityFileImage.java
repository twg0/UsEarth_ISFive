package com.isfive.usearth.domain.activity.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivityFileImage is a Querydsl query type for ActivityFileImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActivityFileImage extends EntityPathBase<ActivityFileImage> {

    private static final long serialVersionUID = 1894292353L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivityFileImage activityFileImage = new QActivityFileImage("activityFileImage");

    public final QActivity activity;

    public final com.isfive.usearth.domain.common.QFileImage fileImage;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QActivityFileImage(String variable) {
        this(ActivityFileImage.class, forVariable(variable), INITS);
    }

    public QActivityFileImage(Path<? extends ActivityFileImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActivityFileImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActivityFileImage(PathMetadata metadata, PathInits inits) {
        this(ActivityFileImage.class, metadata, inits);
    }

    public QActivityFileImage(Class<? extends ActivityFileImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activity = inits.isInitialized("activity") ? new QActivity(forProperty("activity"), inits.get("activity")) : null;
        this.fileImage = inits.isInitialized("fileImage") ? new com.isfive.usearth.domain.common.QFileImage(forProperty("fileImage")) : null;
    }

}

