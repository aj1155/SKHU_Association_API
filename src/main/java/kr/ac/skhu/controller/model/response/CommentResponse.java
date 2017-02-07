package kr.ac.skhu.controller.model.response;

import kr.ac.skhu.domain.Comment;
import lombok.*;

/**
 * Created by Manki Kim on 2017-02-07.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentResponse {

    private int id;
    private String content;
    private int writer_id;
    private String writer_name;
    private String createdDate;
    private String lastModifiedDate;

    public static CommentResponse of(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .writer_id(comment.getWriter_id())
                .writer_name(comment.getWriter_name())
                .createdDate(comment.getCreatedDate().toString())
                .lastModifiedDate(comment.getLastModifiedDate().toString())
                .build();
    }
}
