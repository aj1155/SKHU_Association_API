package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.model.request.UserRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.controller.model.response.UserResponse;
import kr.ac.skhu.domain.UserDIS;
import kr.ac.skhu.service.JwtTokenService;
import kr.ac.skhu.service.UserDISService;
import kr.ac.skhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Manki Kim on 2017-01-18.
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDISService userDISService;

    @Autowired
    private JwtTokenService jwtTokenService;

    /***** create *****/
    //Todo response로 Json util 형태로 보내주기

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public AsctApiResponse<UserResponse> create(@Valid UserRequest userRequest){
        UserResponse user = this.userService.createUser(userRequest);
        return new AsctApiResponse<>(user);
    }

    /***** read *****/

    /* user 공개 정보 검색 */
    @RequestMapping(value = "/userId/{userId}",method = RequestMethod.GET)
    public AsctApiResponse<UserDIS> readUserId(@PathVariable String userId){
        UserDIS userDIS = this.userDISService.read(Integer.parseInt(userId));
        return new AsctApiResponse<>(userDIS);
    }

    /* 카테고리별 user*/
    @RequestMapping(value = "/category/{categoryId}",method = RequestMethod.GET)
    public AsctApiResponse<List<UserResponse>> readUserByCategory(@PathVariable String categoryId){
        List<UserResponse> list = this.userService.readUserByCategoryId(Integer.parseInt(categoryId));
        return new AsctApiResponse<>(list);
    }

    /* 카테고리별 user 동기화*/
    @RequestMapping(value = "/category/{categoryId}/lastTime/{lastTime}",method = RequestMethod.GET)
    public AsctApiResponse<List<UserResponse>> readUserByCategoryByTime(@PathVariable String categoryId,@PathVariable String lastTime){
        List<UserResponse> list = this.userService.readUserByCategoryIdByTime(Integer.parseInt(categoryId),lastTime);
        return new AsctApiResponse<>(list);
    }

    /* 기수별 user*/
    @RequestMapping(value = "/category/{categoryId}/grade/{grade}",method = RequestMethod.GET)
    public AsctApiResponse<List<UserResponse>> readUserByGrade(@PathVariable String categoryId, @PathVariable String grade){
        List<UserResponse> list = this.userService.readUserByGrade(Integer.parseInt(categoryId),Integer.parseInt(grade));
        return new AsctApiResponse<>(list);
    }

    /* 기수별 임원 */
    @RequestMapping(value = "/manager/category/{categoryId}/grade/{grade}",method = RequestMethod.GET)
    public AsctApiResponse<List<UserResponse>> readManagerByGrade(@PathVariable String categoryId, @PathVariable String grade){
        List<UserResponse> list = this.userService.readManagerByGrade(Integer.parseInt(categoryId),Integer.parseInt(grade));
        return new AsctApiResponse<>(list);
    }

    /* 총동문 임원 */
    @RequestMapping(value = "/seniormanager/category/{categoryId}/grade/{grade}",method = RequestMethod.GET)
    public AsctApiResponse<List<UserResponse>> readSeniorManager(@PathVariable String categoryId, @PathVariable String grade){
        List<UserResponse> list = this.userService.readSeniorManager(Integer.parseInt(categoryId),Integer.parseInt(grade));
        return new AsctApiResponse<>(list);
    }

    /***** update *****/

    /* user 정보 업데이트 */
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public AsctApiResponse<UserResponse> readSeniorManager(@Valid UserRequest userRequest){
        return this.userService.update(userRequest);
    }




}
