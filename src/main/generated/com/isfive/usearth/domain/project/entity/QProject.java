package com.isfive.usearth.domain.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = 1759489926L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProject project = new QProject("project");

    public final ListPath<ProjectComment, QProjectComment> comments = this.<ProjectComment, QProjectComment>createList("comments", ProjectComment.class, QProjectComment.class, PathInits.DIRECT2);

    public final com.isfive.usearth.domain.common.QPeriod fundingDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ProjectLike, QProjectLike> likes = this.<ProjectLike, QProjectLike>createList("likes", ProjectLike.class, QProjectLike.class, PathInits.DIRECT2);

    public final com.isfive.usearth.domain.maker.entity.QMaker maker;

    public final ListPath<ProjectFileImage, QProjectFileImage> projectImages = this.<ProjectFileImage, QProjectFileImage>createList("projectImages", ProjectFileImage.class, QProjectFileImage.class, PathInits.DIRECT2);

    public final com.isfive.usearth.domain.common.QFileImage repImage;

    public final ListPath<Reward, QReward> rewards = this.<Reward, QReward>createList("rewards", Reward.class, QReward.class, PathInits.DIRECT2);

    public final ListPath<Tag, QTag> searchTags = this.<Tag, QTag>createList("searchTags", Tag.class, QTag.class, PathInits.DIRECT2);

    public final StringPath story = createString("story");

    public final StringPath summary = createString("summary");

    public final NumberPath<Integer> targetAmount = createNumber("targetAmount", Integer.class);

    public final StringPath title = createString("title");

    public QProject(String variable) {
        this(Project.class, forVariable(variable), INITS);
    }

    public QProject(Path<? extends Project> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProject(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProject(PathMetadata metadata, PathInits inits) {
        this(Project.class, metadata, inits);
    }

    public QProject(Class<? extends Project> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fundingDate = inits.isInitialized("fundingDate") ? new com.isfive.usearth.domain.common.QPeriod(forProperty("fundingDate")) : null;
        this.maker = inits.isInitialized("maker") ? new com.isfive.usearth.domain.maker.entity.QMaker(forProperty("maker"), inits.get("maker")) : null;
        this.repImage = inits.isInitialized("repImage") ? new com.isfive.usearth.domain.common.QFileImage(forProperty("repImage")) : null;
    }

}

