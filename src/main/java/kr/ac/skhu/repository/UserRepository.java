package kr.ac.skhu.repository;

import kr.ac.skhu.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Manki Kim on 2017-01-18.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByCategoryId(int categoryId);
    List<User> findByCategoryIdAndGrade(int categoryId, int grade);
    User findByLoginIdAndPassword(String login_id, String password);
    User findByLoginIdAndPasswordAndCategoryId(String login_id, String password,int categoryId);
}
