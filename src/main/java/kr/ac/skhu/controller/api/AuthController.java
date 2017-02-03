package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.model.request.UserRequest;
import kr.ac.skhu.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * Created by Manki Kim on 2017-01-19.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenService jwtTokenService;

    //Todo resoponse 관련 객체 생성 및 Mapping Json util 생성 할 것
    //Todo password 인증 로직 생성,Category_id 까지 넘기고 query_dsl
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Map<String,Object> authenticate(@Valid @RequestBody UserRequest userRequest){
        return jwtTokenService.createJWT(userRequest);
    }
}
