package kr.ac.skhu.repository.Impl;

import com.querydsl.core.BooleanBuilder;
import kr.ac.skhu.domain.QUserDIS;
import kr.ac.skhu.domain.UserDIS;
import kr.ac.skhu.repository.custom.UserDISRepositoryCustom;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

/**
 * Created by USER on 2017-02-19.
 */
public class UserDISRepositoryImpl extends QueryDslRepositorySupport implements UserDISRepositoryCustom {

    QUserDIS qUserDIS = QUserDIS.userDIS;

    public UserDISRepositoryImpl(){
        super(UserDIS.class);
    }

    @Override
    public List<UserDIS> findByUpdateTime(DateTime time) {
        BooleanBuilder whereClause = new BooleanBuilder();
        whereClause.and(qUserDIS.lastModifiedDate.after(time));
        return from(qUserDIS)
                .where(whereClause)
                .fetch();
    }
}
