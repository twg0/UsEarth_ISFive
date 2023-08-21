package com.isfive.usearth.domain.maker.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMaker is a Querydsl query type for Maker
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMaker extends EntityPathBase<Maker> {

    private static final long serialVersionUID = 22200678L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMaker maker = new QMaker("maker");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.isfive.usearth.domain.member.entity.QMember member;

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final StringPath profileImage = createString("profileImage");

    public final ListPath<com.isfive.usearth.domain.project.entity.Project, com.isfive.usearth.domain.project.entity.QProject> projects = this.<com.isfive.usearth.domain.project.entity.Project, com.isfive.usearth.domain.project.entity.QProject>createList("projects", com.isfive.usearth.domain.project.entity.Project.class, com.isfive.usearth.domain.project.entity.QProject.class, PathInits.DIRECT2);

    public final StringPath submitFile = createString("submitFile");

    public QMaker(String variable) {
        this(Maker.class, forVariable(variable), INITS);
    }

    public QMaker(Path<? extends Maker> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMaker(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMaker(PathMetadata metadata, PathInits inits) {
        this(Maker.class, metadata, inits);
    }

    public QMaker(Class<? extends Maker> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.isfive.usearth.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

