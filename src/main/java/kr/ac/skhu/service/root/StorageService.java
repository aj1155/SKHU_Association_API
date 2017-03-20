package kr.ac.skhu.service.root;

import kr.ac.skhu.controller.exception.StorageException;
import kr.ac.skhu.controller.exception.StorageFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Manki Kim on 2017-02-19.
 */
public interface StorageService {
        public void delete(List<String> list) throws StorageException;
        public void init() throws StorageException;
        public void store(MultipartFile file,String identifier) throws StorageException;
        public Path load(String filename);
        public Resource loadAsResource(String filename) throws StorageFileNotFoundException;
        public Stream<Path> loadAll() throws StorageException;
}
