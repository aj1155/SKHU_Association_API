package kr.ac.skhu.service;

import kr.ac.skhu.component.PasswordEncoding;
import kr.ac.skhu.controller.model.request.UserRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.controller.model.response.UserResponse;
import kr.ac.skhu.domain.Position;
import kr.ac.skhu.domain.User;
import kr.ac.skhu.domain.UserDIS;
import kr.ac.skhu.repository.PositionRepository;
import kr.ac.skhu.repository.UserDISRepository;
import kr.ac.skhu.repository.UserRepository;
import org.joda.time.DateTime;
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

    @Autowired
    private UserDISRepository userDISRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private PasswordEncoding passwordEncoding;

    /***** create *****/


    public UserResponse createUser(UserRequest userRequest){
        User user = User.ofCreate(userRequest.getLogin_id(),userRequest.getUser_name(),userRequest.getPassword());
        user.setPhoneNumber(doReqularProcessingPhoneNum(user.getPhoneNumber()));
        this.userRepository.save(user);
        UserDIS userDIS = UserDIS.ofCreate(user.getId());
        this.userDISRepository.save(userDIS);
        return UserResponse.ofUser(user);
    }

    /***** read *****/

    public UserResponse readUserId(int userId){
        User user = this.userRepository.findOne(userId);
        return UserResponse.ofUser(user);
    }

    public List<UserResponse> readUserByCategoryId(int categoryId){
        List<User> list = this.userRepository.findByCategoryId(categoryId);
        return convertUserEntityToResponse(list);
    }

    public List<UserResponse> readUserByCategoryIdByTime(int categoryId,String time){
        List<User> list = this.userRepository.findByUpdateTime(categoryId,DateTime.parse(time));
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
        String encodedPassword = this.passwordEncoding.encode(userRequest.getPassword());
        Position position = this.positionRepository.findOne(userRequest.getUser_type());
        User user = User.ofUpdate(userRequest.getId(),position,userRequest.getLogin_id(),userRequest.getUser_name(),encodedPassword,userRequest.getEmail()
        ,userRequest.getCompany_number(),userRequest.getPhone_number(),userRequest.getStatus(),userRequest.getGrade(),userRequest.getBirth(),Integer.parseInt(userRequest.getCategoryId()));
        String msg = validateBeforeUpdate(user);
        msg=null;
        if(msg != null){
            return new AsctApiResponse<>(AsctApiResponse.DUPLICATE_LOGINID);
        }else{
            user.setPhoneNumber(doReqularProcessingPhoneNum(user.getPhoneNumber()));
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

    private String doReqularProcessingPhoneNum(String phoneNumber){
        StringBuilder phoneBuilder = new StringBuilder(phoneNumber);
        phoneBuilder.insert(3,"-");
        phoneBuilder.insert(phoneBuilder.length()-4,"-");
        return phoneBuilder.toString();
    }

}
