package kr.ac.skhu.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardPostImage is a Querydsl query type for BoardPostImage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBoardPostImage extends EntityPathBase<BoardPostImage> {

    private static final long serialVersionUID = -1046359525L;

    public static final QBoardPostImage boardPostImage = new QBoardPostImage("boardPostImage");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Integer> boardPostId = createNumber("boardPostId", Integer.class);

    //inherited
    public final DateTimePath<org.joda.time.DateTime> createdDate = _super.createdDate;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    //inherited
    public final DateTimePath<org.joda.time.DateTime> lastModifiedDate = _super.lastModifiedDate;

    public final StringPath path = createString("path");

    public QBoardPostImage(String variable) {
        super(BoardPostImage.class, forVariable(variable));
    }

    public QBoardPostImage(Path<? extends BoardPostImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardPostImage(PathMetadata metadata) {
        super(BoardPostImage.class, metadata);
    }

}

