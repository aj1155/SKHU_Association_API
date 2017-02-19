package kr.ac.skhu.domain;

import kr.ac.skhu.controller.model.request.UserDISRequest;
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
@ToString
@Table(name ="user_discloser")
@Entity
public class UserDIS implements Serializable {
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
                //.userId(userId)
                .build();
    }

    public static UserDIS ofUpdate(UserDISRequest userDISRequest){
        return UserDIS.builder()
                .id(userDISRequest.getId())
                .isCompanyNumber(userDISRequest.getIsCompanyNumber())
                .isEmail(userDISRequest.getIsEmail())
                .isImage(userDISRequest.getIsImage())
                .isPhoneNumber(userDISRequest.getIsPhoneNumber())
                .isStatus(userDISRequest.getIsStatus())
                .userId(userDISRequest.getUserId())
                .build();
    }
}
