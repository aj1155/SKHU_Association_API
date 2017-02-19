package kr.ac.skhu.repository.custom;

import kr.ac.skhu.domain.BoardPost;

import java.util.List;

/**
 * Created by Manki Kim on 2017-02-13.
 */
public interface BoardPostRepositoryCustom {
    List<BoardPost> readByBoardId(int boardId,int startIndex);
}
