package kr.ac.skhu.repository;

import kr.ac.skhu.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Manki Kim on 2017-01-29.
 */
public interface ImageRepository extends JpaRepository<Image,Integer> {
}
