package com.isfive.usearth.domain.funding.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFunding is a Querydsl query type for Funding
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFunding extends EntityPathBase<Funding> {

    private static final long serialVersionUID = -1660130554L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFunding funding = new QFunding("funding");

    public final ListPath<FundingReward, QFundingReward> fundingRewards = this.<FundingReward, QFundingReward>createList("fundingRewards", FundingReward.class, QFundingReward.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.isfive.usearth.domain.member.entity.QMember member;

    public final EnumPath<FundingStatus> status = createEnum("status", FundingStatus.class);

    public QFunding(String variable) {
        this(Funding.class, forVariable(variable), INITS);
    }

    public QFunding(Path<? extends Funding> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFunding(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFunding(PathMetadata metadata, PathInits inits) {
        this(Funding.class, metadata, inits);
    }

    public QFunding(Class<? extends Funding> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.isfive.usearth.domain.member.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

