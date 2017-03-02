package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.model.request.UserDISRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.domain.UserDIS;
import kr.ac.skhu.service.UserDISService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Manki Kim on 2017-02-08.
 */
@RestController
@RequestMapping("/api/v1/userdis")
public class UserDISController {

    @Autowired
    private UserDISService userDISService;

    @PutMapping
    public AsctApiResponse update(@Valid @RequestBody UserDISRequest userDISRequest){
        return this.userDISService.update(userDISRequest);
    }

    /* 카테고리별 user 동기화*/
    @GetMapping("/lastTime/{lastTime}")
    public AsctApiResponse<List<UserDIS>> readUserByCategoryByTime(@PathVariable String lastTime){
        List<UserDIS> list = this.userDISService.readByUpdateTime(lastTime);
        return new AsctApiResponse<>(list);
    }
}
