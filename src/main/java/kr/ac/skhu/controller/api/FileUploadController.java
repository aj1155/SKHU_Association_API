package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.exception.StorageException;
import kr.ac.skhu.controller.exception.StorageFileNotFoundException;
import kr.ac.skhu.service.ImageService;
import kr.ac.skhu.service.JwtTokenService;
import kr.ac.skhu.service.root.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manki Kim on 2017-01-30.
 */
@RestController
@RequestMapping(value="/v1/files")
public class FileUploadController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @GetMapping("/list")
    public ResponseEntity<List<String>> fileList() throws StorageException {
        List<String> collection = new ArrayList<>();
        this.storageService.loadAll().forEach(action -> collection.add(action.getFileName().toString()));
        return new ResponseEntity<List<String>>(collection, HttpStatus.OK);
    }

    @GetMapping("/{filename:.+}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String filename) throws StorageFileNotFoundException, UnsupportedEncodingException {
        Resource file = this.storageService.loadAsResource(filename);

        String encodedFilename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
        return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+ encodedFilename + "\"")
                            .body(file);
    }

    @PostMapping("/board")
    public ResponseEntity<?> fileUploadByBoard(@RequestParam("file") MultipartFile file,@RequestParam("boardPostId") String boardPostId,RedirectAttributes redirectAttributes) throws StorageException {
        this.storageService.init();
        this.storageService.store(file,boardPostId);

        return new ResponseEntity<String>("successfully uploaded " + file.getOriginalFilename() + "!" , HttpStatus.OK);
    }

    @PostMapping("/profile")
    public ResponseEntity<?> fileUploadByProfileImage(@RequestParam("file") MultipartFile file,RedirectAttributes redirectAttributes,HttpServletRequest request) throws StorageException {
        String token = (String)request.getHeader("token");
        String userId = this.jwtTokenService.getUserIdFromToken(token);
        this.storageService.init();
        this.imageService.create(Integer.parseInt(userId),file.getSize());
        this.storageService.store(file,userId);

        return new ResponseEntity<String>("successfully uploaded " + file.getOriginalFilename() + "!" , HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(FileAlreadyExistsException.class)
    public ResponseEntity<?> handleStorageFileAlreadyExists(FileAlreadyExistsException exc) {
        return new ResponseEntity<String>("해당 파일은 이미 존재합니다.",HttpStatus.CONFLICT);
    }
}
