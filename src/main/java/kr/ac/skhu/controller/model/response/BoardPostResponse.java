package kr.ac.skhu.controller.model.response;

import kr.ac.skhu.domain.BoardPost;
import kr.ac.skhu.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * Created by Manki Kim on 2017-01-23.
 */
@Getter
@Setter
@Builder
public class BoardPostResponse {

    private String title;
    private String content;
    private String writer_name;
    private Collection<Comment> commentList;

    public static BoardPostResponse ofBoard(BoardPost boardPost){
        return BoardPostResponse.builder()
                .title(boardPost.getTitle())
                .content(boardPost.getContent())
                .writer_name(boardPost.getWriter_name())
                .commentList(boardPost.getCommentList())
                .build();
    }
}
