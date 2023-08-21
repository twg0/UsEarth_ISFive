package com.isfive.usearth.domain.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectFileImage is a Querydsl query type for ProjectFileImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectFileImage extends EntityPathBase<ProjectFileImage> {

    private static final long serialVersionUID = 1552570457L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectFileImage projectFileImage = new QProjectFileImage("projectFileImage");

    public final com.isfive.usearth.domain.common.QFileImage fileImage;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QProject project;

    public QProjectFileImage(String variable) {
        this(ProjectFileImage.class, forVariable(variable), INITS);
    }

    public QProjectFileImage(Path<? extends ProjectFileImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectFileImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectFileImage(PathMetadata metadata, PathInits inits) {
        this(ProjectFileImage.class, metadata, inits);
    }

    public QProjectFileImage(Class<? extends ProjectFileImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fileImage = inits.isInitialized("fileImage") ? new com.isfive.usearth.domain.common.QFileImage(forProperty("fileImage")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
    }

}

