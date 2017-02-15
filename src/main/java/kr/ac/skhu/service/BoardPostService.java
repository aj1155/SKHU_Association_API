package kr.ac.skhu.service;

import kr.ac.skhu.controller.model.request.BoardPostRequest;
import kr.ac.skhu.controller.model.response.BoardPostResponse;
import kr.ac.skhu.domain.BoardPost;
import kr.ac.skhu.repository.BoardPostRepository;
import kr.ac.skhu.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Manki Kim on 2017-01-23.
 */
@Service
public class BoardPostService {

    @Autowired
    private BoardPostRepository boardPostRepository;

    @Autowired
    private BoardRepository boardRepository;

    /***** create *****/

    public BoardPostResponse create(BoardPostRequest boardPostRequest){
        //Todo arguments 줄이기
        BoardPost boardPost = BoardPost.ofCreate(boardPostRequest.getTitle(),boardPostRequest.getContent(),boardPostRequest.getOwnBoardId(),boardPostRequest.getWriter_id(),boardPostRequest.getWriter_name());
        this.boardPostRepository.save(boardPost);
        System.out.println(boardPost.getCreatedDate());
        return BoardPostResponse.ofBoard(boardPost);
    }

    /***** read *****/
    public BoardPostResponse readById(int boardPostId){
        BoardPost boardPost = this.boardPostRepository.findById(boardPostId);
        return BoardPostResponse.ofBoard(boardPost);
    }

    public List<BoardPostResponse> readByBoardId(int ownBoardId,int startIndex){
        List<BoardPost> boardPosts = this.boardPostRepository.readByBoardId(ownBoardId,startIndex);
        return convertBoardPostEntityToResponse(boardPosts);
    }

    /***** update *****/
    //Todo Json Utill 로 return 할 것!
    public Map<String,Object> update(BoardPostRequest boardPostRequest){
        BoardPost boardPost = BoardPost.ofUpdate(boardPostRequest.getId(),boardPostRequest.getTitle(),boardPostRequest.getContent()
                                                    ,boardPostRequest.getOwnBoardId(),boardPostRequest.getWriter_id(),boardPostRequest.getWriter_name());
        this.boardPostRepository.save(boardPost);
        Map<String,Object> resultStatus = new HashMap<String,Object>();
        resultStatus.put("code",200);
        resultStatus.put("msg","정삭적으로 변경했습니다.");

        return resultStatus;
    }
    /***** delete *****/
    //TODO JSON Utill로 return
    public Map<String,Object> delete(int boardPostId){
        this.boardPostRepository.delete(boardPostId);
        Map<String,Object> resultStatus = new HashMap<String,Object>();
        resultStatus.put("code",200);
        resultStatus.put("msg","정상적으로 삭제했습니다");
        return resultStatus;
    }




    private List<BoardPostResponse> convertBoardPostEntityToResponse(List<BoardPost> boardPostList){
        List<BoardPostResponse> boardPostResponses = Optional.ofNullable(boardPostList).orElse(Collections.emptyList()).stream()
                .map(boardPost -> BoardPostResponse.ofBoard(boardPost)).distinct().collect(Collectors.toList());
        return boardPostResponses;
    }
}