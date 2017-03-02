package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.model.request.BoardPostRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.controller.model.response.BoardPostResponse;
import kr.ac.skhu.service.BoardPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    /***** create *****/
    @RequestMapping(value = "",method = RequestMethod.POST)
    public AsctApiResponse<BoardPostResponse> create(@Valid @RequestBody BoardPostRequest boardPostRequest){
        BoardPostResponse boardPostResponse = this.boardPostService.create(boardPostRequest);
        return new AsctApiResponse<>(boardPostResponse);
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
    //Todo Json Util만들어서 채워서 보내기
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public AsctApiResponse update(@Valid @RequestBody BoardPostRequest boardPostRequest){
        Map<String,Object> result = this.boardPostService.update(boardPostRequest);
        return new AsctApiResponse<>(result);
    }

    /***** delete *****/
    //Todo JSON Util 만들어서 result 리턴
    @RequestMapping(value = "/{boardPostId}",method = RequestMethod.DELETE)
    public AsctApiResponse delete(@PathVariable String boardPostId){
        Map<String,Object> result = this.boardPostService.delete(Integer.parseInt(boardPostId));
        return new AsctApiResponse<>(result);
    }

}
