package kr.ac.skhu.repository.Impl;

import com.querydsl.core.BooleanBuilder;
import kr.ac.skhu.domain.QUser;
import kr.ac.skhu.domain.User;
import kr.ac.skhu.repository.custom.UserRepositoryCustom;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

/**
 * Created by Manki Kim on 2017-02-19.
 */
public class UserRepositoryImpl extends QueryDslRepositorySupport implements UserRepositoryCustom {

    QUser qUser = QUser.user;

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<User> findByUpdateTime(int category, DateTime time) {
        BooleanBuilder whereClause = new BooleanBuilder();
        whereClause.and(qUser.categoryId.eq(category));
        whereClause.and(qUser.lastModifiedDate.after(time));
        return from(qUser)
                .where(whereClause)
                .fetch();
    }
}
