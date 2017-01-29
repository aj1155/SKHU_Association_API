package kr.ac.skhu.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by manki Kim on 2016. 12. 30..
 */
public class CORSFilter implements Filter {

    private String dashboardUrl;

    public CORSFilter(String dashboardUrl) {
        this.dashboardUrl = dashboardUrl;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        response.addHeader("Access-Control-Max-Age", Integer.toString(1800));
        //response.addHeader("Access-Control-Allow-Origin", this.dashboardUrl);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, x-requested-with");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(request, res);
    }

    @Override
    public void destroy() {

    }
}
