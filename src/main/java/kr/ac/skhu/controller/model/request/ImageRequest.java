package kr.ac.skhu.controller.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by Manki Kim on 2017-01-29.
 */
@Data
public class ImageRequest {

    private int id;
    @NotNull
    private MultipartFile imageFile;
    @NotNull
    private String user_id;
    @NotNull
    private Long size;
    private String path;
}
