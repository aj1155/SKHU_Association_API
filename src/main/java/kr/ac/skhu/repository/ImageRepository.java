package kr.ac.skhu.repository;

import kr.ac.skhu.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Manki Kim on 2017-01-29.
 */
@Repository
public interface ImageRepository extends JpaRepository<Image,Integer> {
    List<Image> findByUserId(int userId);
}
