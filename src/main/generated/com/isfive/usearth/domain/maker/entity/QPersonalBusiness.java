package com.isfive.usearth.domain.maker.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPersonalBusiness is a Querydsl query type for PersonalBusiness
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPersonalBusiness extends EntityPathBase<PersonalBusiness> {

    private static final long serialVersionUID = 1503811518L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPersonalBusiness personalBusiness = new QPersonalBusiness("personalBusiness");

    public final QMaker _super;

    public final QBusinessInformation businessInformation;

    //inherited
    public final StringPath email;

    //inherited
    public final NumberPath<Long> id;

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

    public QPersonalBusiness(String variable) {
        this(PersonalBusiness.class, forVariable(variable), INITS);
    }

    public QPersonalBusiness(Path<? extends PersonalBusiness> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPersonalBusiness(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPersonalBusiness(PathMetadata metadata, PathInits inits) {
        this(PersonalBusiness.class, metadata, inits);
    }

    public QPersonalBusiness(Class<? extends PersonalBusiness> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QMaker(type, metadata, inits);
        this.businessInformation = inits.isInitialized("businessInformation") ? new QBusinessInformation(forProperty("businessInformation")) : null;
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

