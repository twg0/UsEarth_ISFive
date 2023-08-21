package com.isfive.usearth.domain.funding.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFundingReward is a Querydsl query type for FundingReward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFundingReward extends EntityPathBase<FundingReward> {

    private static final long serialVersionUID = -1377187371L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFundingReward fundingReward = new QFundingReward("fundingReward");

    public final QFunding funding;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> optionCount = createNumber("optionCount", Integer.class);

    public final com.isfive.usearth.domain.project.entity.QRewardSku rewardSku;

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QFundingReward(String variable) {
        this(FundingReward.class, forVariable(variable), INITS);
    }

    public QFundingReward(Path<? extends FundingReward> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFundingReward(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFundingReward(PathMetadata metadata, PathInits inits) {
        this(FundingReward.class, metadata, inits);
    }

    public QFundingReward(Class<? extends FundingReward> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.funding = inits.isInitialized("funding") ? new QFunding(forProperty("funding"), inits.get("funding")) : null;
        this.rewardSku = inits.isInitialized("rewardSku") ? new com.isfive.usearth.domain.project.entity.QRewardSku(forProperty("rewardSku"), inits.get("rewardSku")) : null;
    }

}

