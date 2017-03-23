package kr.ac.skhu.repository;

import kr.ac.skhu.domain.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Manki Kim on 2017-03-23.
 */
@Repository
public interface PositionRepository extends JpaRepository<Position,Integer> {
}
