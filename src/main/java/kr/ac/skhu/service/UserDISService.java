package kr.ac.skhu.service;

import kr.ac.skhu.domain.UserDIS;
import kr.ac.skhu.repository.UserDISRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
