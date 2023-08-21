package com.isfive.usearth.domain.maker.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusinessInformation is a Querydsl query type for BusinessInformation
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QBusinessInformation extends BeanPath<BusinessInformation> {

    private static final long serialVersionUID = 981428974L;

    public static final QBusinessInformation businessInformation = new QBusinessInformation("businessInformation");

    public final StringPath corporateName = createString("corporateName");

    public final StringPath registration = createString("registration");

    public final StringPath registrationNumber = createString("registrationNumber");

    public QBusinessInformation(String variable) {
        super(BusinessInformation.class, forVariable(variable));
    }

    public QBusinessInformation(Path<? extends BusinessInformation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusinessInformation(PathMetadata metadata) {
        super(BusinessInformation.class, metadata);
    }

}

