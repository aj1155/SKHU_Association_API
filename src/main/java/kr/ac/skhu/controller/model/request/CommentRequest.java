package kr.ac.skhu.controller.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by Manki Kim on 2017-02-07.
 */
@Data
public class CommentRequest {

    private int id;

    @NotNull
    private String content;

    @NotNull
    private int writer_id;

    @NotNull
    private String writer_name;

    @NotNull
    private int boadPostId;
}
