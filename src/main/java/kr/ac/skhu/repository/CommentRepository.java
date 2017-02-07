package kr.ac.skhu.repository;

import kr.ac.skhu.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Manki Kim on 2017-02-07.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    List<Comment> findByboardPostId(int boardPostId);
}
