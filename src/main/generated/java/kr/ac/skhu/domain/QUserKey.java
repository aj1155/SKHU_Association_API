package kr.ac.skhu.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserKey is a Querydsl query type for UserKey
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserKey extends EntityPathBase<UserKey> {

    private static final long serialVersionUID = 377338766L;

    public static final QUserKey userKey1 = new QUserKey("userKey1");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public final StringPath userKey = createString("userKey");

    public QUserKey(String variable) {
        super(UserKey.class, forVariable(variable));
    }

    public QUserKey(Path<? extends UserKey> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserKey(PathMetadata metadata) {
        super(UserKey.class, metadata);
    }

}

