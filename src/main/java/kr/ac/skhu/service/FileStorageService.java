package kr.ac.skhu.service;

import kr.ac.skhu.controller.exception.StorageException;
import kr.ac.skhu.controller.exception.StorageFileNotFoundException;
import kr.ac.skhu.service.root.StorageService;
import kr.ac.skhu.util.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Manki Kim on 2017-02-19.
 */
@Service
public class FileStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileStorageService(StorageProperties storageProperties){
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @Override
    public void init() throws StorageException {
        try{
            if(!Files.exists(rootLocation)){
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e){
            throw new StorageException("저장소를 초기화 할 수 없습니다!.", e);
        }
    }

    @Override
    public void store(MultipartFile file,String identifier) throws StorageException {
        try{
            if(file.isEmpty())
                throw new StorageException("빈 파일은 저장 할 수 없습니다."+ file.getOriginalFilename());

            //Files.copy(file.getInputStream(),this.rootLocation.resolve(file.getOriginalFilename()));
            Files.copy(file.getInputStream(),this.rootLocation.resolve(identifier+"&&"+file.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("다음과 같은 파일을 저장하는데 실패 하였습니다 -" + file.getOriginalFilename(),e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public List<File> loadAllFile() {

        List<File> fileList = null;

        try {
            fileList = Files.walk(this.rootLocation,1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> path.toFile())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    @Override
    public Resource loadAsResource(String filename) throws StorageFileNotFoundException {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            } else {
                throw new StorageFileNotFoundException("다음의 파일을 읽을 수 없습니다 - " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("다음의 파일을 읽을 수 없습니다 - " + filename,e);
        }
    }

    @Override
    public Stream<Path> loadAll() throws StorageException {
        try {
            return Files.walk(this.rootLocation,1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path)); // 상대경로 생성
        } catch (IOException e) {
            throw new StorageException("저장된 파일들을 불러오는데 실패하였습니다.",e);
        }
    }
}
