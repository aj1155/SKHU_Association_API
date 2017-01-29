package kr.ac.skhu.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

/**
 * Created by Manki Kim on 2016. 12. 30..
 */
@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
public class BaseEntity {

    /* 처음 entity가 저장될때 생성일 주입 */
    @CreatedDate
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "CREATE_DATE", updatable = false)
    private DateTime createdDate;

    /* 엔터티가 수정될때 수정일자를 주입 */
    @LastModifiedDate
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "LAST_MODIFIED_DATE")
    private DateTime lastModifiedDate;

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}