package kr.ac.skhu.repository;


import kr.ac.skhu.domain.BoardPost;
import kr.ac.skhu.repository.custom.BoardPostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Manki Kim on 2017-01-23.
 */
public interface BoardPostRepository extends JpaRepository<BoardPost,Integer>,BoardPostRepositoryCustom {

	BoardPost findById(int id);
}
