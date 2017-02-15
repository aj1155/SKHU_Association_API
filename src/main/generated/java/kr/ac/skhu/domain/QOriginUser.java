package kr.ac.skhu.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOriginUser is a Querydsl query type for OriginUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOriginUser extends EntityPathBase<OriginUser> {

    private static final long serialVersionUID = -1863739177L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOriginUser originUser = new QOriginUser("originUser");

    public final StringPath birth = createString("birth");

    public final QCategory category;

    public final StringPath companyNumber = createString("companyNumber");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath loginId = createString("loginId");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath status = createString("status");

    public QOriginUser(String variable) {
        this(OriginUser.class, forVariable(variable), INITS);
    }

    public QOriginUser(Path<? extends OriginUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOriginUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOriginUser(PathMetadata metadata, PathInits inits) {
        this(OriginUser.class, metadata, inits);
    }

    public QOriginUser(Class<? extends OriginUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
    }

}

