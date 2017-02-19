package kr.ac.skhu.service;

import kr.ac.skhu.domain.Image;
import kr.ac.skhu.repository.ImageRepository;
import kr.ac.skhu.util.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Manki Kim on 2017-01-29.
 */
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    private final Path rootLocation;

    @Autowired
    public ImageService(StorageProperties storageProperties){
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    /***** create *****/

    public void create(int userId,long fileSize){
        Image image = Image.ofCreate(rootLocation.resolve(String.valueOf(userId)).toString(),userId,fileSize);
        this.imageRepository.save(image);
    }

}
