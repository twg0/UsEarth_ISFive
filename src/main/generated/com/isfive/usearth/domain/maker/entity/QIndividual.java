package com.isfive.usearth.domain.maker.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIndividual is a Querydsl query type for Individual
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIndividual extends EntityPathBase<Individual> {

    private static final long serialVersionUID = -958726473L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIndividual individual = new QIndividual("individual");

    public final QMaker _super;

    //inherited
    public final StringPath email;

    //inherited
    public final NumberPath<Long> id;

    public final StringPath IDImage = createString("IDImage");

    // inherited
    public final com.isfive.usearth.domain.member.entity.QMember member;

    //inherited
    public final StringPath name;

    //inherited
    public final StringPath phone;

    //inherited
    public final StringPath profileImage;

    //inherited
    public final ListPath<com.isfive.usearth.domain.project.entity.Project, com.isfive.usearth.domain.project.entity.QProject> projects;

    //inherited
    public final StringPath submitFile;

    public QIndividual(String variable) {
        this(Individual.class, forVariable(variable), INITS);
    }

    public QIndividual(Path<? extends Individual> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIndividual(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIndividual(PathMetadata metadata, PathInits inits) {
        this(Individual.class, metadata, inits);
    }

    public QIndividual(Class<? extends Individual> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QMaker(type, metadata, inits);
        this.email = _super.email;
        this.id = _super.id;
        this.member = _super.member;
        this.name = _super.name;
        this.phone = _super.phone;
        this.profileImage = _super.profileImage;
        this.projects = _super.projects;
        this.submitFile = _super.submitFile;
    }

}

