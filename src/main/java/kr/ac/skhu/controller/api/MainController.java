package kr.ac.skhu.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Manki Kim on 2017-01-19.
 */
@RestController
@RequestMapping(value = "/")
public class MainController {

    @RequestMapping(value = "")
    public String main(){
        return "회원정보를 정확이 기입한 후 로그인 해주세요";
    }
}
