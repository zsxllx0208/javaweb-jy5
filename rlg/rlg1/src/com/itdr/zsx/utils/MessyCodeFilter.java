package com.itdr.zsx.utils;

import com.itdr.zsx.common.ResponseCode;
import com.itdr.zsx.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MessyCodeFilter", value = "/manage/*")
public class MessyCodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //处理乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");



        //统一返回数据对象
        ResponseCode rs = new ResponseCode();

        //转型，使用子类更多的方法
        HttpServletRequest request = (HttpServletRequest) req;

        //获取路径
        String pathInfo = request.getPathInfo();

        if (pathInfo == "/login.do") {
            chain.doFilter(req,resp);
            return;
        }
        //其他请求处理
        //验证session是否有用户信息
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null){
            rs.setStatus(3);
            rs.setMsg("请登陆后进行此操作");
            resp.getWriter().write(rs.toString());
            return;
        }
        if (user.getType() != 1) {
            rs.setStatus(3);
            rs.setMsg("没有操作权限！");
            resp.getWriter().write(rs.toString());
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
