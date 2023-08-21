package com.isfive.usearth.domain.activity.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QActivity is a Querydsl query type for Activity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QActivity extends EntityPathBase<Activity> {

    private static final long serialVersionUID = -1453736098L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QActivity activity = new QActivity("activity");

    public final com.isfive.usearth.domain.common.QPeriod activityDate;

    public final ListPath<ActivityFileImage, QActivityFileImage> activityImages = this.<ActivityFileImage, QActivityFileImage>createList("activityImages", ActivityFileImage.class, QActivityFileImage.class, PathInits.DIRECT2);

    public final com.isfive.usearth.domain.common.QPeriod applicateDate;

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> maxMember = createNumber("maxMember", Integer.class);

    public final ListPath<com.isfive.usearth.domain.member.entity.Member, com.isfive.usearth.domain.member.entity.QMember> members = this.<com.isfive.usearth.domain.member.entity.Member, com.isfive.usearth.domain.member.entity.QMember>createList("members", com.isfive.usearth.domain.member.entity.Member.class, com.isfive.usearth.domain.member.entity.QMember.class, PathInits.DIRECT2);

    public final StringPath organization = createString("organization");

    public final StringPath place = createString("place");

    public final EnumPath<com.isfive.usearth.domain.common.Permit> status = createEnum("status", com.isfive.usearth.domain.common.Permit.class);

    public final StringPath title = createString("title");

    public QActivity(String variable) {
        this(Activity.class, forVariable(variable), INITS);
    }

    public QActivity(Path<? extends Activity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QActivity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QActivity(PathMetadata metadata, PathInits inits) {
        this(Activity.class, metadata, inits);
    }

    public QActivity(Class<? extends Activity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.activityDate = inits.isInitialized("activityDate") ? new com.isfive.usearth.domain.common.QPeriod(forProperty("activityDate")) : null;
        this.applicateDate = inits.isInitialized("applicateDate") ? new com.isfive.usearth.domain.common.QPeriod(forProperty("applicateDate")) : null;
    }

}

