package kr.ac.skhu.controller.api;

import kr.ac.skhu.controller.model.request.ImageRequest;
import kr.ac.skhu.controller.model.response.AsctApiResponse;
import kr.ac.skhu.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Manki Kim on 2017-01-30.
 */
@Controller
@RequestMapping(value="/api/v1/upload")
public class UploadController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value="/profile",method = RequestMethod.POST)
    public AsctApiResponse enrollProfileImage(@Valid @RequestBody ImageRequest[] imageRequests){
        return this.imageService.createProfileImage(imageRequests);
    }
}
