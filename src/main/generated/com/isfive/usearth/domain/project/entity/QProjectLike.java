package com.isfive.usearth.domain.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProjectLike is a Querydsl query type for ProjectLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProjectLike extends EntityPathBase<ProjectLike> {

    private static final long serialVersionUID = -1963679683L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProjectLike projectLike = new QProjectLike("projectLike");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.isfive.usearth.domain.member.entity.QMember member;

    public final QProject project;

    public QProjectLike(String variable) {
        this(ProjectLike.class, forVariable(variable), INITS);
    }

    public QProjectLike(Path<? extends ProjectLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProjectLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProjectLike(PathMetadata metadata, PathInits inits) {
        this(ProjectLike.class, metadata, inits);
    }

    public QProjectLike(Class<? extends ProjectLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.isfive.usearth.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
    }

}

