package kr.ac.skhu.controller.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Created by Manki Kim on 2017-01-23.
 */
@Getter
@Setter
public class BoardPostRequest {

    private int id;
    @NotNull
    private String title;
    private String content;
    @NotNull
    private String writer_name;
    @NotNull
    private int writer_id;
    @NotNull
    private int ownBoardId;

}
