package kr.ac.skhu.service;

import kr.ac.skhu.controller.model.request.UserRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.controller.model.response.UserResponse;
import kr.ac.skhu.domain.User;
import kr.ac.skhu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 * Created by Manki Kim on 2017-01-18.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /***** create *****/


    public UserResponse createUser(UserRequest userRequest){
        User user = User.ofCreate(userRequest.getLogin_id(),userRequest.getUser_name(),userRequest.getPassword());
        this.userRepository.save(user);
        return UserResponse.ofUser(user);
    }

    /***** read *****/

    public List<UserResponse> readUserByCategoryId(int categoryId){
        List<User> list = this.userRepository.findByCategoryId(categoryId);
        return convertUserEntityToResponse(list);
    }

    public List<UserResponse> readUserByGrade(int categoryId,int grade){
        List<User> list = this.userRepository.findByCategoryIdAndGrade(categoryId,grade);
        return convertUserEntityToResponse(list);
    }

    public List<UserResponse> readManagerByGrade(int categoryId,int grade){
        List<User> list = this.userRepository.findByCategoryIdAndGrade(categoryId,grade);
        List<User> managers = Optional.ofNullable(list).orElse(Collections.emptyList()).stream()
                .filter(user -> user.getPosition().getName().startsWith("기수"))
                .collect(Collectors.toList());
        return convertUserEntityToResponse(managers);
    }
    public List<UserResponse> readSeniorManager(int categoryId,int grade){
        List<User> list = this.userRepository.findByCategoryIdAndGrade(categoryId,grade);
        List<User> managers = Optional.ofNullable(list).orElse(Collections.emptyList()).stream()
                .filter(user -> user.getPosition().getName().startsWith("총동문"))
                .collect(Collectors.toList());
        return convertUserEntityToResponse(managers);
    }
    public int getMaxGrade(int categoryId){
        List<User> list = this.userRepository.findByCategoryId(categoryId);
        OptionalInt max = list.stream().mapToInt(User::getGrade).max();
        return max.getAsInt();
    }

    /***** update *****/

    public AsctApiResponse<UserResponse> update(UserRequest userRequest){
        User user = User.ofUpdate(userRequest.getId(),userRequest.getLogin_id(),userRequest.getUser_name(),userRequest.getPassword());
        String msg = validateBeforeUpdate(user);
        if(msg != null){
            return new AsctApiResponse<>(AsctApiResponse.DUPLICATE_LOGINID);
        }else{
            this.userRepository.save(user);
            return new AsctApiResponse<>(UserResponse.ofUser(user));
        }
    }

    private List<UserResponse> convertUserEntityToResponse(List<User> userList){
        List<UserResponse> userResponses = Optional.ofNullable(userList).orElse(Collections.emptyList()).stream()
                .map(user -> UserResponse.ofUser(user)).distinct().collect(Collectors.toList());

        return userResponses;
    }



    /* 유효성 검사 */

    private String validateBeforeUpdate(User user) {
        User oldUser = this.userRepository.findOne(user.getId());
        if (oldUser != null && user.getLoginId() != oldUser.getLoginId())
            return "기존에 사용 중인 휴대번호 입니다.";

        return null;
    }

}
