package kr.ac.skhu.repository;

import kr.ac.skhu.domain.Board;
import kr.ac.skhu.domain.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Manki Kim on 2017-01-24.
 */
@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
    Board findByCategoryIdAndBoardType(int categoryId, BoardType boardType);
}
