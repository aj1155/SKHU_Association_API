package kr.ac.skhu.repository;

import kr.ac.skhu.domain.UserDIS;
import kr.ac.skhu.repository.custom.UserDISRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Manki Kim on 2017-02-08.
 */
@Repository
public interface UserDISRepository extends JpaRepository<UserDIS, Integer>,UserDISRepositoryCustom {
    UserDIS findByUserId(int userId);
}
