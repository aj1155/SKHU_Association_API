package kr.ac.skhu.repository;

import kr.ac.skhu.domain.User;
import kr.ac.skhu.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Manki Kim on 2017-01-18.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>,UserRepositoryCustom {
    List<User> findByCategoryId(int categoryId);
    List<User> findByCategoryIdAndGrade(int categoryId, int grade);
    User findByLoginIdAndCategoryId(String loginId,int categoryId);
}
