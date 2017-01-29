package kr.ac.skhu.controller.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by 1002731 on 2016. 12. 30..
 * Email : eenan@sk.com
 */
public class HealthCheckFilter extends OncePerRequestFilter {

    final Logger LOGGER = LoggerFactory.getLogger(HealthCheckFilter.class);
    final String HEALTH_CHECK_URI = "/healthcheck.jsp";
    final UrlPathHelper urlPathHelper = new UrlPathHelper();

    private boolean active = true;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String originatingRequestUri = urlPathHelper.getOriginatingRequestUri(request);
        if(originatingRequestUri.startsWith(HEALTH_CHECK_URI)) {
            String tempActive = Optional.ofNullable(request.getParameter("active")).orElse("");
            if (Arrays.asList("true", "false").contains(tempActive)) {
                active = Boolean.valueOf(tempActive);
            }

            LOGGER.info("active {}", active);
            LOGGER.info("processing health check request for [{}]", originatingRequestUri);

            URL resource = request.getServletContext().getResource(HEALTH_CHECK_URI);
            if(!active || resource == null) {
                LOGGER.error("health check file is not exist");
                response.setStatus(404);
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.getWriter().write("health check file is not exist");
                response.flushBuffer();
            } else {
                response.setStatus(200);
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.getWriter().write("skp_healthcheck_ok");
                response.flushBuffer();
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}