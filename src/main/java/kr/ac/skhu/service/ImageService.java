package kr.ac.skhu.service;

import kr.ac.skhu.domain.Image;
import kr.ac.skhu.repository.ImageRepository;
import kr.ac.skhu.util.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> getPaths(int userId){
        List<Image> images = this.imageRepository.findByUserId(userId);
        return images.stream().map(Image::getPath).collect(Collectors.toList());
    }

    /***** create *****/

    public void create(int userId,long fileSize,String fileName){
        Image image = Image.ofCreate(rootLocation.resolve(fileName).toString(),userId,fileSize);
        this.imageRepository.save(image);
    }

}
