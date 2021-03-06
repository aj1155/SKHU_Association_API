package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.model.response.AsctApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Manki Kim on 2017-01-26.
 */
@RestController
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "/tokenless",method = RequestMethod.GET)
    public AsctApiResponse generateError(){
        return new AsctApiResponse(AsctApiResponse.TOKEN_LESS);
    }

}
