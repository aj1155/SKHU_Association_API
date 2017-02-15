package kr.ac.skhu.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardPost is a Querydsl query type for BoardPost
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardPost extends EntityPathBase<BoardPost> {

    private static final long serialVersionUID = -81667296L;

    public static final QBoardPost boardPost = new QBoardPost("boardPost");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<org.joda.time.DateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final DateTimePath<org.joda.time.DateTime> lastModifiedDate = _super.lastModifiedDate;

    public final NumberPath<Integer> ownBoardId = createNumber("ownBoardId", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Integer> writer_id = createNumber("writer_id", Integer.class);

    public final StringPath writer_name = createString("writer_name");

    public QBoardPost(String variable) {
        super(BoardPost.class, forVariable(variable));
    }

    public QBoardPost(Path<? extends BoardPost> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardPost(PathMetadata metadata) {
        super(BoardPost.class, metadata);
    }

}

