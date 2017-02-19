package kr.ac.skhu.repository.Impl;

import kr.ac.skhu.domain.QUserDIS;
import kr.ac.skhu.domain.UserDIS;
import kr.ac.skhu.repository.custom.UserDISRepositoryCustom;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

/**
 * Created by USER on 2017-02-19.
 */
public class UserDISRepositoryImpl extends QueryDslRepositorySupport implements UserDISRepositoryCustom {

    QUserDIS qUserDIS = QUserDIS.userDIS;

    public UserDISRepositoryImpl(){
        super(UserDIS.class);
    }

}
