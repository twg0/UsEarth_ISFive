package com.isfive.usearth.domain.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRewardSku is a Querydsl query type for RewardSku
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRewardSku extends EntityPathBase<RewardSku> {

    private static final long serialVersionUID = 710471835L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRewardSku rewardSku = new QRewardSku("rewardSku");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReward reward;

    public final ListPath<SkuValue, QSkuValue> skuValues = this.<SkuValue, QSkuValue>createList("skuValues", SkuValue.class, QSkuValue.class, PathInits.DIRECT2);

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public QRewardSku(String variable) {
        this(RewardSku.class, forVariable(variable), INITS);
    }

    public QRewardSku(Path<? extends RewardSku> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRewardSku(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRewardSku(PathMetadata metadata, PathInits inits) {
        this(RewardSku.class, metadata, inits);
    }

    public QRewardSku(Class<? extends RewardSku> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reward = inits.isInitialized("reward") ? new QReward(forProperty("reward"), inits.get("reward")) : null;
    }

}

