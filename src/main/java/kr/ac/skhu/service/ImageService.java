package kr.ac.skhu.service;

import kr.ac.skhu.controller.model.request.ImageRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.domain.Image;
import kr.ac.skhu.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Manki Kim on 2017-01-29.
 */
@Service
public class ImageService {

    private static final String resoucePath = "/static/image/";

    @Autowired
    private ImageRepository imageRepository;


    /***** create *****/

    public AsctApiResponse createProfileImage(ImageRequest[] imageRequests){

        Arrays.stream(imageRequests).forEach(imageRequest -> {
            String path = resoucePath + imageRequest.getUser_id();
            Image profileImage = Image.ofCreate(path,imageRequest.getUser_id(),imageRequest.getSize());
            imageRepository.save(profileImage);
            try {
                outPutStream(imageRequest.getImageFile(),path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        return new AsctApiResponse(AsctApiResponse.OK);
    }



    private void outPutStream(MultipartFile file, String path) throws FileNotFoundException {
        FileOutputStream output = new FileOutputStream(path);
        try {
            output.write(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
