package com.ddz.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Reason: TODO ADD REASON.
 *
 * @author Chen Lingang
 * @version $Id: MyCORSFilter, v 0.1 17/2/8 下午2:18
 */
@Component
public class MyCORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        //    response.setHeader("User-Agent", "micromessager");
        System.out.println("session:"+request.getSession().getId());
        chain.doFilter(req, res);
        //  System.out.println(request.getHeader("User-Agent"));
    }

    @Override
    public void destroy() {

    }
}
