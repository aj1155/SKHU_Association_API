package kr.ac.skhu.controller.model.request;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by Manki Kim on 2017-02-19.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDISRequest {

    @NotNull
    private int id;
    @NotNull
    private int userId;
    @NotNull
    private Boolean isPhoneNumber;
    @NotNull
    private Boolean isCompanyNumber;
    @NotNull
    private Boolean isStatus;
    @NotNull
    private Boolean isImage;
    @NotNull
    private Boolean isEmail;
}
