package kr.ac.skhu.repository.Impl;

import com.querydsl.core.BooleanBuilder;
import kr.ac.skhu.domain.BoardPost;
import kr.ac.skhu.domain.QBoardPost;
import kr.ac.skhu.repository.custom.BoardPostRepositoryCustom;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Manki Kim on 2017-02-13.
 */
public class BoardPostRepositoryImpl extends QueryDslRepositorySupport implements BoardPostRepositoryCustom {

    QBoardPost qBoardPost = QBoardPost.boardPost;

    @PersistenceContext
    private EntityManager entityManager;

    public BoardPostRepositoryImpl(){
        super(BoardPost.class);
    }

    @Override
    public List<BoardPost> readByBoardId(int boardId, int startIndex) {
        return from(qBoardPost)
                .where(qBoardPost.ownBoardId.eq(boardId))
                .orderBy(qBoardPost.lastModifiedDate.desc())
                .offset(startIndex)
                .limit(15)
                .fetch();
    }

    @Override
    public List<BoardPost> readByBoardIdAndWriter(int boardId, int startIndex, String srchText) {
        return from(qBoardPost)
                .where(qBoardPost.ownBoardId.eq(boardId))
                .where(qBoardPost.writer_name.like(srchText))
                .orderBy(qBoardPost.lastModifiedDate.desc())
                .offset(startIndex)
                .limit(15)
                .fetch();
    }

    @Override
    public List<BoardPost> readByBoardIdAndContent(int boardId, int startIndex, String srchText) {
        BooleanBuilder whereClause = new BooleanBuilder();
        whereClause.and(qBoardPost.ownBoardId.eq(boardId));
        whereClause.and(qBoardPost.title.contains(srchText));
        whereClause.or(qBoardPost.content.contains(srchText));
        return from(qBoardPost)
                .where(whereClause)
                .orderBy(qBoardPost.lastModifiedDate.desc())
                .offset(startIndex)
                .limit(15)
                .fetch();
    }
}
