package kr.ac.skhu.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Manki Kim on 2017-03-05.
 */
@Getter
@Setter
@Table(name = "board_post_image")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardPostImage extends BaseEntity implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @NotNull
    @Column(name = "path")
    private String path;

    @NotNull
    @Column(name = "board_post_id")
    private int boardPostId;

    public static BoardPostImage of(String path,int boardPostId){
        return BoardPostImage.builder()
                .path(path)
                .boardPostId(boardPostId)
                .build();
    }

}
