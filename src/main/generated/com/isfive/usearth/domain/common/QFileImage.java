package com.isfive.usearth.domain.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileImage is a Querydsl query type for FileImage
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFileImage extends BeanPath<FileImage> {

    private static final long serialVersionUID = 1600139071L;

    public static final QFileImage fileImage = new QFileImage("fileImage");

    public final StringPath originalName = createString("originalName");

    public final StringPath storedName = createString("storedName");

    public QFileImage(String variable) {
        super(FileImage.class, forVariable(variable));
    }

    public QFileImage(Path<? extends FileImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileImage(PathMetadata metadata) {
        super(FileImage.class, metadata);
    }

}

