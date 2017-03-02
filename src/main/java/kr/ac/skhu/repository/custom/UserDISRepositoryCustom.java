package kr.ac.skhu.repository.custom;

import kr.ac.skhu.domain.UserDIS;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by Manki Kim on 2017-02-19.
 */
public interface UserDISRepositoryCustom {

    List<UserDIS> findByUpdateTime(DateTime time);

}
