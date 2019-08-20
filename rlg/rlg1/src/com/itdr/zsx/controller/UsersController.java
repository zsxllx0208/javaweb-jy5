package com.itdr.zsx.controller;

import com.itdr.zsx.common.ResponseCode;
import com.itdr.zsx.pojo.Users;
import com.itdr.zsx.service.UserService;
import com.itdr.zsx.utils.PathUtil;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/user/*")
public class UsersController extends HttpServlet {
    private UserService uc = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取请求路径信息
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);
        //创建统一返回对象
        ResponseCode rs = null;

        //判断请求
        switch (path) {
            case "list":
                rs = listDo(request);
                break;
            case "login":
                rs = loginDo(request);
                break;
            case "disableuser":
                rs = disableuserDo(request);
                break;
            case "managelist":
                rs = manageListDo(request);
                break;

        }

        //获取参数
//        String pageSize = request.getParameter("pageSize");
//        String pageNum = request.getParameter("pageNum");

        //返回响应数据
        response.getWriter().write(rs.toString());

    }


    //获取用户列表的请求
    private ResponseCode listDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();
        //获取一个session对象
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        //判断是否已登录
        if (user == null) {
            rs.setStatus(3);
            rs.setMsg("请登录后操作");
            return rs;
        }
        //判断是否是管理员
        if (user.getType() < 1) {
            rs.setStatus(3);
            rs.setMsg("没有操作权限");
            return rs;
        }

        //获取参数

        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        rs = uc.selectAll(pageSize, pageNum);
        //调用业务层方法处理业务

        return rs;


    }

    //用户登录的请求
    private ResponseCode loginDo(HttpServletRequest request) {


        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //调用业务层方法处理业务
        ResponseCode rs = uc.selectOne(username, password);


        HttpSession session = request.getSession();
        session.setAttribute("user", rs.getData());

        return rs;
    }

    //禁用用户的请求
    private ResponseCode disableuserDo(HttpServletRequest request) {

        //获取参数
        String uid = request.getParameter("uid");

        ResponseCode rs = uc.selectOne(uid);

        return rs;

    }

    //获取管理员列表
    private ResponseCode manageListDo(HttpServletRequest request) {

        ResponseCode rs = new ResponseCode();
        //获取一个session对象
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        //判断是否已登录
        if (user == null) {
            rs.setStatus(3);
            rs.setMsg("请登录后操作");
            return rs;
        }
        //判断是否是管理员
        if (user.getType() < 2) {
            rs.setStatus(3);
            rs.setMsg("没有操作权限");
            return rs;
        }

        //获取参数

        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        rs = uc.selectManages(pageSize, pageNum);
        //调用业务层方法处理业务

        return rs;


    }
}
