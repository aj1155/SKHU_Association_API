package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.exception.StorageException;
import kr.ac.skhu.controller.exception.StorageFileNotFoundException;
import kr.ac.skhu.controller.model.request.BoardPostRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.controller.model.response.BoardPostResponse;
import kr.ac.skhu.domain.BoardPost;
import kr.ac.skhu.service.BoardPostService;
import kr.ac.skhu.service.JwtTokenService;
import kr.ac.skhu.service.file.BoardFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Map;

/**
 * Created by Manki Kim on 2017-01-23.
 */
@RestController
@RequestMapping(value = "/api/v1/boardpost")
public class BoardPostController {

    @Autowired
    private BoardPostService boardPostService;

    @Autowired
    private BoardFileService boardFileService;

    @Autowired
    private JwtTokenService jwtTokenService;

    /***** create *****/
    @RequestMapping(value = "",method = RequestMethod.POST)
    public AsctApiResponse<BoardPostResponse> create(@Valid @RequestBody BoardPostRequest boardPostRequest){
        BoardPostResponse boardPostResponse = this.boardPostService.create(boardPostRequest);
        return new AsctApiResponse<>(boardPostResponse);
    }

    @PostMapping("/withFile")
    public ResponseEntity<?> withFIle(@RequestParam("file") MultipartFile[] files,@RequestParam("content") String content,HttpServletRequest request
            ,@RequestParam("boardId") String boardId, @RequestParam("title") String title,RedirectAttributes redirectAttributes) throws StorageException {
        String token = (String)request.getHeader("token");
        String userId = this.jwtTokenService.getUserIdFromToken(token);
        String userName = this.jwtTokenService.getUsernameFromToken(token);
        BoardPost boardPost = BoardPost.ofCreate(title,content,Integer.parseInt(boardId),Integer.parseInt(userId),userName);
        BoardPostResponse boardPostResponse = this.boardPostService.createBoard(boardPost);
        this.boardFileService.init();
        this.boardFileService.storeMuti(files,String.valueOf(boardPostResponse.getId()));
        return new ResponseEntity<String>("successfully uploaded  !" , HttpStatus.OK);
    }

    /***** read *****/
    @RequestMapping(value = "/{boardpostId}",method = RequestMethod.GET)
    public AsctApiResponse<BoardPostResponse> readOne(@PathVariable String boardpostId){
        BoardPostResponse boardPostResponse = this.boardPostService.readById(Integer.parseInt(boardpostId));
        return new AsctApiResponse<>(boardPostResponse);
    }

    @RequestMapping(value = "/ownBoardId/{ownBoardId}/srchType/{srchType}/srchText/{srchText}/startIndex/{startIndex}",method = RequestMethod.GET)
    public AsctApiResponse<List<BoardPostResponse>> readByBoardId(@PathVariable String ownBoardId,@PathVariable String srchType,
                                                                  @PathVariable String srchText,@PathVariable String startIndex){
        List<BoardPostResponse> list = this.boardPostService.readByBoardId(Integer.parseInt(ownBoardId),Integer.parseInt(startIndex),
                Integer.parseInt(srchType),srchText);
        if(list.size()==0){
            return new AsctApiResponse<>(AsctApiResponse.NODATA);
        }
        return new AsctApiResponse<>(list);
    }

    /***** update *****/
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public AsctApiResponse update(@Valid @RequestBody BoardPostRequest boardPostRequest){
        Map<String,Object> result = this.boardPostService.update(boardPostRequest);
        return new AsctApiResponse<>(result);
    }

    @PutMapping("/withFile")
    public ResponseEntity<?> withFIleUpdate(@RequestParam("file") MultipartFile[] files,@RequestParam("content") String content,HttpServletRequest request
            ,@RequestParam("boardPostId") String boardPostId,@RequestParam("boardId") String boardId ,@RequestParam("title") String title,RedirectAttributes redirectAttributes) throws StorageException {
        String token = (String)request.getHeader("token");
        String userId = this.jwtTokenService.getUserIdFromToken(token);
        String userName = this.jwtTokenService.getUsernameFromToken(token);
        BoardPost boardPost = BoardPost.ofUpdate(Integer.parseInt(boardPostId),title,content,Integer.parseInt(boardId),Integer.parseInt(userId),userName);
        BoardPostResponse boardPostResponse = this.boardPostService.updateBoard(boardPost);
        this.boardFileService.init();
        this.boardFileService.storeMuti(files,String.valueOf(boardPostResponse.getId()));
        return new ResponseEntity<String>("successfully uploaded  !" , HttpStatus.OK);
    }

    /***** delete *****/
    @RequestMapping(value = "/{boardPostId}",method = RequestMethod.DELETE)
    public AsctApiResponse delete(@PathVariable String boardPostId){
        Map<String,Object> result = this.boardPostService.delete(Integer.parseInt(boardPostId));
        return new AsctApiResponse<>(result);
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
