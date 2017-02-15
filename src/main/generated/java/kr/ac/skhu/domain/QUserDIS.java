package kr.ac.skhu.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserDIS is a Querydsl query type for UserDIS
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserDIS extends EntityPathBase<UserDIS> {

    private static final long serialVersionUID = 377331133L;

    public static final QUserDIS userDIS = new QUserDIS("userDIS");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isCompanyNumber = createBoolean("isCompanyNumber");

    public final BooleanPath isEmail = createBoolean("isEmail");

    public final BooleanPath isImage = createBoolean("isImage");

    public final BooleanPath isPhoneNumber = createBoolean("isPhoneNumber");

    public final BooleanPath isStatus = createBoolean("isStatus");

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QUserDIS(String variable) {
        super(UserDIS.class, forVariable(variable));
    }

    public QUserDIS(Path<? extends UserDIS> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserDIS(PathMetadata metadata) {
        super(UserDIS.class, metadata);
    }

}

