package kr.ac.skhu.controller.interceptor;

import kr.ac.skhu.service.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Manki Kim on 2017-01-19.
 */
@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object arg2, ModelAndView arg3) throws Exception {

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        //TODO token 인증
        String token = (String)request.getHeader("token");
        if(token == null || token.isEmpty()){
            log.debug("no Authentication");
            response.sendRedirect(request.getContextPath()+"/error/tokenless");
        }else{
            if(this.jwtTokenService.getUsernameFromToken(token)==null){
                log.debug("no Authentication");
                response.sendRedirect(request.getContextPath()+"/error/tokenless");
            }
        }
        return true;
    }
}
