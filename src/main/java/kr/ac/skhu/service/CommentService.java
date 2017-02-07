package kr.ac.skhu.service;

import kr.ac.skhu.controller.model.request.CommentRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.controller.model.response.CommentResponse;
import kr.ac.skhu.domain.Comment;
import kr.ac.skhu.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Manki Kim on 2017-02-07.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /***** create *****/
    public AsctApiResponse create(CommentRequest commentRequest){
        Comment comment = Comment.ofCreate(commentRequest);
        this.commentRepository.save(comment);
        return new AsctApiResponse(AsctApiResponse.OK);
    }

    /***** read *****/
    public AsctApiResponse<List<CommentResponse>> read(int boardPostId){
        List<Comment> comments = this.commentRepository.findByboardPostId(boardPostId);
        return new AsctApiResponse<>(convertUserEntityToResponse(comments));
    }
    public AsctApiResponse<CommentResponse> readOne(int commentId){
        Optional<Comment> commentOptional = Optional.ofNullable(this.commentRepository.findOne(commentId));
        if(commentOptional.isPresent()){
            return new AsctApiResponse<>(CommentResponse.of(commentOptional.get()));
        }
        return new AsctApiResponse(AsctApiResponse.NOT_FOUND);

    }
    /***** update *****/
    public AsctApiResponse update(CommentRequest commentRequest){
        Comment comment = Comment.ofUpdate(commentRequest);
        this.commentRepository.save(comment);
        return new AsctApiResponse(AsctApiResponse.OK);
    }

    /***** delete *****/
    public AsctApiResponse delete(int commentId){
        this.commentRepository.delete(commentId);
        return new AsctApiResponse(AsctApiResponse.OK);
    }


    private List<CommentResponse> convertUserEntityToResponse(List<Comment> commentList){
        List<CommentResponse> commentResponses = Optional.ofNullable(commentList).orElse(Collections.emptyList()).stream()
                .map(comment -> CommentResponse.of(comment)).distinct().collect(Collectors.toList());
        return commentResponses;
    }
}
