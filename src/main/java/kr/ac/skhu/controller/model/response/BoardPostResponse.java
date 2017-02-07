package kr.ac.skhu.controller.model.response;

import kr.ac.skhu.domain.BoardPost;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Manki Kim on 2017-01-23.
 */
@Getter
@Setter
@Builder
public class BoardPostResponse {

    private int id;
    private String title;
    private String content;
    private String writer_name;
    private String write_time;

    public static BoardPostResponse ofBoard(BoardPost boardPost){
        return BoardPostResponse.builder()
                .id(boardPost.getId())
                .title(boardPost.getTitle())
                .content(boardPost.getContent())
                .write_time(boardPost.getCreatedDate().toString())
                .writer_name(boardPost.getWriter_name())
                .build();
    }

    public static BoardPostResponse listOfBoardPost(BoardPost boardPost){
        return BoardPostResponse.builder()
                .id(boardPost.getId())
                .title(boardPost.getTitle())
                .writer_name(boardPost.getWriter_name())
                .write_time(boardPost.getCreatedDate().toString())
                .build();
    }
}
