package kr.ac.skhu.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Manki Ki on 2017-01-18.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "user")
public class User extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "grade")
    private int grade;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "company_number")
    private String companyNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "birth")
    private String birth;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @Column(name = "category_id")
    private int categoryId;

    @ManyToOne
    @JoinColumn(name="user_type")
    private Position position;

    public static User ofCreate(String loginId,String name,String password){
        return User.builder()
                .loginId(loginId)
                .name(name)
                .password(password)
                .build();
    }
    public static User ofUpdate(int id,String loginId,String name,String password){
        return User.builder()
                .id(id)
                .loginId(loginId)
                .name(name)
                .password(password)
                .build();
    }

}
