package kr.ac.skhu.service;

import kr.ac.skhu.domain.BoardPostImage;
import kr.ac.skhu.repository.BoardPostImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Manki Kim on 2017-03-20.
 */
@Service
public class BoardPostImageService {

    @Autowired
    private BoardPostImageRepository boardPostImageRepository;

    public List<String> getPaths(int boardPostId){
        List<BoardPostImage> boardPostImages = this.boardPostImageRepository.findByBoardPostId(boardPostId);
        return boardPostImages.stream().map(BoardPostImage::getPath).collect(Collectors.toList());
    }

    @Transactional
    public void delete(int boardPostId){
        this.boardPostImageRepository.deleteByBoardPostId(boardPostId);
    }
}
