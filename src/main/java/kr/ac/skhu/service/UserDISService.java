package kr.ac.skhu.service;

import kr.ac.skhu.controller.model.request.UserDISRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.domain.UserDIS;
import kr.ac.skhu.repository.UserDISRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Manki Kim on 2017-02-08.
 */
@Service
public class UserDISService {

    @Autowired
    private UserDISRepository userDISRepository;


    /***** read *****/
    public UserDIS read(int userId){
        Optional<UserDIS> userDISOptional = Optional.ofNullable(userDISRepository.findByUserId(userId));
        UserDIS userDIS;
        if(!userDISOptional.isPresent()){
            userDIS = UserDIS.ofCreate(userId);
        }else{
            userDIS = userDISOptional.get();
        }
        return userDIS;
    }

    public List<UserDIS> readByUpdateTime(String date){
        return this.userDISRepository.findByUpdateTime(DateTime.parse(date));
    }

    /***** update *****/

    public AsctApiResponse update(UserDISRequest userDISRequest){
        UserDIS userDIS = UserDIS.ofUpdate(userDISRequest);
        this.userDISRepository.save(userDIS);
        return new AsctApiResponse(AsctApiResponse.OK);
    }
}
