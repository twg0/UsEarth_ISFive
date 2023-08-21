package com.isfive.usearth.domain.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReward is a Querydsl query type for Reward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReward extends EntityPathBase<Reward> {

    private static final long serialVersionUID = -1837422302L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReward reward = new QReward("reward");

    public final NumberPath<Integer> deliveryFee = createNumber("deliveryFee", Integer.class);

    public final StringPath description = createString("description");

    public final StringPath expectedSendDate = createString("expectedSendDate");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> initStock = createNumber("initStock", Integer.class);

    public final ListPath<Option, QOption> options = this.<Option, QOption>createList("options", Option.class, QOption.class, PathInits.DIRECT2);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final QProject project;

    public final ListPath<RewardSku, QRewardSku> rewardSkus = this.<RewardSku, QRewardSku>createList("rewardSkus", RewardSku.class, QRewardSku.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QReward(String variable) {
        this(Reward.class, forVariable(variable), INITS);
    }

    public QReward(Path<? extends Reward> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReward(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReward(PathMetadata metadata, PathInits inits) {
        this(Reward.class, metadata, inits);
    }

    public QReward(Class<? extends Reward> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.project = inits.isInitialized("project") ? new QProject(forProperty("project"), inits.get("project")) : null;
    }

}

