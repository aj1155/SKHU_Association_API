package kr.ac.skhu.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Manki Kim on 2017-02-08.
 */

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name ="user_discloser")
@Entity
public class UserDIS extends BaseEntity implements Serializable {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "user_id")
    private int userId;

    @NotNull
    @Column(name = "is_phone_number")
    private Boolean isPhoneNumber;

    @NotNull
    @Column(name = "is_company_number")
    private Boolean isCompanyNumber;

    @NotNull
    @Column(name = "is_status")
    private Boolean isStatus;

    @NotNull
    @Column(name = "is_image")
    private Boolean isImage;

    @NotNull
    @Column(name = "is_email")
    private Boolean isEmail;

    public static UserDIS ofCreate(int userId){
        return UserDIS.builder()
                .userId(userId)
                .build();
    }
}
