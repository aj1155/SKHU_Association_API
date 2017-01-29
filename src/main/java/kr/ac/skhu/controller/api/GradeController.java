package kr.ac.skhu.controller.api;

import kr.ac.skhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Manki Kim on 2017-01-20.
 */
@RestController
@RequestMapping(value = "/api/v1/grade")
public class GradeController {

    @Autowired
    private UserService userService;

    /***** read *****/
    @RequestMapping(value = "/maxGrade/category/{categoryId}",method = RequestMethod.GET)
    public int getMaxGrade(@PathVariable String categoryId){
        return this.userService.getMaxGrade(Integer.parseInt(categoryId));
    }
}
