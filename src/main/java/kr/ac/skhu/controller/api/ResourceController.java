package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.exception.InvalidStatusException;
import kr.ac.skhu.controller.exception.StorageFileNotFoundException;
import kr.ac.skhu.service.file.BoardFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.file.FileAlreadyExistsException;

/**
 * Created by Manki Kim on 2017-03-22.
 */
@RestController
@RequestMapping(value = "/resources")
public class ResourceController {

    @Autowired
    private BoardFileService boardFileService;

    @GetMapping("/boardpost/{filename:.+}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws StorageFileNotFoundException, UnsupportedEncodingException {
        Resource file = this.boardFileService.loadAsResource(filename);

        String encodedFilename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+ encodedFilename + "\"")
                .body(file);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<?> handleUserNotFound(StorageFileNotFoundException exc) {
        return new ResponseEntity<String>("유저 정보를 다시 확인 해주세요", HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<?> handleStorageFileAlreadyExists(FileAlreadyExistsException exc) {
        return new ResponseEntity<String>("해당 파일은 이미 존재합니다.", HttpStatus.CONFLICT);
    }
}
