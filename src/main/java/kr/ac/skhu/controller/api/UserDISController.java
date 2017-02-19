package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.model.request.UserDISRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.service.UserDISService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
