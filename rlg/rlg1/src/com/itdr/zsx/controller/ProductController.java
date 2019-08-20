package com.itdr.zsx.controller;

import com.itdr.zsx.common.ResponseCode;
import com.itdr.zsx.dao.ProductDao;
import com.itdr.zsx.pojo.Users;
import com.itdr.zsx.service.ProductService;
import com.itdr.zsx.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/manage/product/*")
public class ProductController extends HttpServlet {
    private ProductService ps = new ProductService();

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
            case "search":
                rs = searchDo(request);
                break;
            case "detail":
                rs = detailDo(request);
                break;
            case "set_sale_status":
                rs = set_sale_statusDo(request);
                break;
            case "save":
                rs = saveDo(request);
                break;
        }
        //返回响应数据
        response.getWriter().write(rs.toString());
    }



    //查询所有商品
    private ResponseCode listDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();
        //获取一个session对象
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        //判断是否是管理员
        if (user.getType() < 1) {
            rs.setStatus(3);
            rs.setMsg("没有操作权限");
            return rs;
        }
        //获取参数

        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        rs = ps.selectAll(pageSize, pageNum);

        return rs;
    }

    //根据商品名称和ID查询商品
    private ResponseCode searchDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();
        //获取一个session对象
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        //判断是否是管理员
        if (user.getType() < 1) {
            rs.setStatus(3);
            rs.setMsg("没有操作权限");
            return rs;
        }
        //获取参数

        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");
        String productName = request.getParameter("productName");
        String productId = request.getParameter("productId");

        rs = ps.selectByIdName(pageSize, pageNum, productName, productId);

        return rs;


    }

    //根据ID查询商品
    private ResponseCode detailDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        //获取参数

        String productId = request.getParameter("productId");

        rs = ps.selectById(productId);

        return rs;

    }

    //产品上下架
    private ResponseCode set_sale_statusDo(HttpServletRequest request) {
        //获取参数
        String productId = request.getParameter("productId");
        String status = request.getParameter("status");

        ResponseCode rs = ps.upDown(productId,status);

        return rs;

    }

    //产品新增or更新
    private ResponseCode saveDo(HttpServletRequest request) {
        ResponseCode rs =new ResponseCode();
        String categoryId = request.getParameter("categoryId");
        String name = request.getParameter("name");
        String subtitle = request.getParameter("subtitle");
        String mainImage = request.getParameter("mainImages");
        String status = request.getParameter("status");
        String price = request.getParameter("price");

        rs = ps.saveDo(categoryId,name,subtitle,mainImage,status,price);


        return  rs;
    }
}
