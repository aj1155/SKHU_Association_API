package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.model.request.CommentRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.controller.model.response.CommentResponse;
import kr.ac.skhu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Manki Kim on 2017-02-07.
 */
@RestController
@RequestMapping(value = "/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    /***** create *****/

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public AsctApiResponse create(@Valid @RequestBody CommentRequest commentRequest){
        return this.commentService.create(commentRequest);
    }

    /***** read *****/

    /* readAll */
    @RequestMapping(value = "/{boardPostId}",method = RequestMethod.GET)
    public AsctApiResponse<List<CommentResponse>> readAllByBoardPost(@PathVariable String boardPostId){
        return this.commentService.read(Integer.parseInt(boardPostId));
    }

    /* readOne */
    @RequestMapping(value = "/{commentId}",method = RequestMethod.GET)
    public AsctApiResponse<CommentResponse> readOneById(@PathVariable String commentId){
        return this.commentService.readOne(Integer.parseInt(commentId));
    }

    /***** update *****/
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public AsctApiResponse update(@Valid @RequestBody CommentRequest commentRequest){
        return this.commentService.update(commentRequest);
    }

    /***** delete *****/
    @RequestMapping(value = "/{commentId}",method = RequestMethod.DELETE)
    public AsctApiResponse delete(@PathVariable String commentId){
        return this.commentService.delete(Integer.parseInt(commentId));
    }
    
}
