package kr.ac.skhu.service;

import kr.ac.skhu.domain.Board;
import kr.ac.skhu.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Manki Kim on 2017-02-19.
 */
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> readByCategoryId(int categoryId){
        return this.boardRepository.findByCategoryId(categoryId);
    }
}
