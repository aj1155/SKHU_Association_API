package kr.ac.skhu.repository;


import kr.ac.skhu.domain.BoardPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Manki Kim on 2017-01-23.
 */
public interface BoardPostRepository extends JpaRepository<BoardPost,Integer> {

	BoardPost findById(int id);
    List<BoardPost> findByOwnBoardId(int ownBoardId);

}
