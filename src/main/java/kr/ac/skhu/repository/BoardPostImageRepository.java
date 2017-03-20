package kr.ac.skhu.repository;

import kr.ac.skhu.domain.BoardPostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Manki Kim on 2017-03-05.
 */
@Repository
public interface BoardPostImageRepository extends JpaRepository<BoardPostImage,Integer> {
    List<BoardPostImage> findByBoardPostId(int boardPostId);
    void deleteByBoardPostId(int boardPostId);
}
