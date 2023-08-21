package com.isfive.usearth.domain.project.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSkuValue is a Querydsl query type for SkuValue
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSkuValue extends EntityPathBase<SkuValue> {

    private static final long serialVersionUID = -2121287801L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSkuValue skuValue = new QSkuValue("skuValue");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QOption option;

    public final QOptionValue optionValue;

    public final QRewardSku rewardSku;

    public QSkuValue(String variable) {
        this(SkuValue.class, forVariable(variable), INITS);
    }

    public QSkuValue(Path<? extends SkuValue> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSkuValue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSkuValue(PathMetadata metadata, PathInits inits) {
        this(SkuValue.class, metadata, inits);
    }

    public QSkuValue(Class<? extends SkuValue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.option = inits.isInitialized("option") ? new QOption(forProperty("option"), inits.get("option")) : null;
        this.optionValue = inits.isInitialized("optionValue") ? new QOptionValue(forProperty("optionValue"), inits.get("optionValue")) : null;
        this.rewardSku = inits.isInitialized("rewardSku") ? new QRewardSku(forProperty("rewardSku"), inits.get("rewardSku")) : null;
    }

}

