package kr.ac.skhu.repository;

import kr.ac.skhu.domain.UserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Manki Kim on 2017-03-22.
 */
@Repository
public interface UserKeyRepository extends JpaRepository<UserKey,Integer> {
    UserKey findByUserId(int userId);
}
