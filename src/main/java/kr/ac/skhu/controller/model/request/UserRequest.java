package kr.ac.skhu.controller.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Manki Kim on 2017-01-18.
 */
@Data
public class UserRequest {

    private int id;
    @NotNull
    private String login_id;
    private String password;
    @NotNull
    private String user_name;
    @NotNull
    private String categoryId;
    private String email;
    private String birth;
    private String company_number;
    private String phone_number;
    private String status;
    private int grade;
    private int user_type;



}
