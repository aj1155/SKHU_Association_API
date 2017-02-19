package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.domain.Board;
import kr.ac.skhu.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Manki Kim on 2017-02-19.
 */
@RestController
@RequestMapping(value = "/api/v1/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/category/{categoryId}",method = RequestMethod.GET)
    public AsctApiResponse<List<Board>> readAllByCategoryId(@PathVariable String categoryId){
        List<Board> boards = this.boardService.readByCategoryId(Integer.parseInt(categoryId));
        return new AsctApiResponse<>(boards);
    }
}
