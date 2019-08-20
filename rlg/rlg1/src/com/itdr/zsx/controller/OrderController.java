package com.itdr.zsx.controller;

import com.itdr.zsx.common.ResponseCode;
import com.itdr.zsx.service.OrderService;
import com.itdr.zsx.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OrderController", value = "/manage/order/*")
public class OrderController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    OrderService os = new OrderService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建统一返回对象
        ResponseCode rs = new ResponseCode();

        //获取路径
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);
        switch (path) {
            case "send_goods":
                rs = sendgoodsDo(request);
                break;
            case "list":
                rs =listDo(request);
                break;
            case "search":
                rs =searchDo(request);
                break;
            case "detail":
                rs =detailDo(request);
                break;
        }

        response.getWriter().write(rs.toString());
    }




    //订单发货
    private ResponseCode sendgoodsDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        String orderNo = request.getParameter("orderNo");

        rs = os.selectByNo(orderNo);

        return rs;
    }

    //订单列表
    private ResponseCode listDo(HttpServletRequest request) {
        ResponseCode rs =new ResponseCode();

        //获取参数
        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        rs = os.selectAll(pageSize,pageNum);

        return rs;
    }

    //按ID查询订单
    private ResponseCode searchDo(HttpServletRequest request) {

        ResponseCode rs = new ResponseCode();

        //获取参数

        String orderNo = request.getParameter("orderNo");

        rs = os.selectOne(orderNo);

        return rs;
    }


    private ResponseCode detailDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        //获取参数

        String orderNo = request.getParameter("orderNo");

        rs = os.selectOne(orderNo);

        return rs;
    }
}
