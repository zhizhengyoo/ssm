package com.ynu.filter;

import javax.servlet.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by YANG on 2017/3/17.
 */
public class PermisionFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest  req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpSession session = req.getSession();
        String path = req.getServletPath();
        Object flag = req.getSession().getAttribute("login_success");

        if(path.equals("/account_seller")
                ||path.contains("account")
                ||(path.contains("shoppingCart")&&(!path.equals("/shoppingCart/passport/vcode")))){
            if (flag != null){
                chain.doFilter(request, response);
            }else {
                req.setAttribute("msg","您还未登录，请先登录！");
                session.setAttribute("returnUrl",path);
                String url = (String)req.getSession().getAttribute("returnUrl");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/login");
                requestDispatcher.forward(req, resp);
            }
        }else{
            chain.doFilter(request, response);
        }

    }

    public void destroy() {
    }

}
