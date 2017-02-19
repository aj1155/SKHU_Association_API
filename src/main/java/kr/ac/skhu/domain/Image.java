package kr.ac.skhu.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Manki Kim on 2017-01-29.
 */
@Getter
@Setter
@Builder
@Table(name = "images")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "user_id")
    @NotNull
    private int userId;

    @Column(name = "file_name")
    @NotNull
    private String fileName;

    @Column(name = "size")
    @NotNull
    private Long size;

    @Column(name = "path")
    @NotNull
    private String path;

    public static Image ofCreate(String path,int user_id,Long size){
        return Image.builder()
                .path(path)
                .userId(user_id)
                .size(size)
                .build();
    }
}
