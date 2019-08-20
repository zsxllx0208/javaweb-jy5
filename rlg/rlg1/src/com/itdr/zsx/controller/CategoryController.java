package com.itdr.zsx.controller;

import com.itdr.zsx.common.ResponseCode;

import com.itdr.zsx.service.CategoryService;
import com.itdr.zsx.utils.PathUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CategoryController", value = "/manage/category/*")
public class CategoryController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    CategoryService cs = new CategoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建统一返回对象
        ResponseCode rs = new ResponseCode();

        //获取路径
        String pathInfo = request.getPathInfo();
        String path = PathUtil.getPath(pathInfo);
        switch (path) {
            case "get_category":
                rs = get_categoryDo(request);
                break;
            case "add_category":
                rs = add_categoryDo(request);
                break;
            case "set_category_name":
                rs = set_category_nameDo(request);
                break;
            case "get_deep_category":
                rs = get_deep_categoryDo(request) ;
                break;
        }

        response.getWriter().write(rs.toString());
    }

    //获取品类子节点(平级)
    private ResponseCode get_categoryDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        //获取参数
        String categoryId = request.getParameter("categoryId");

        rs = cs.selectBycategoryId(categoryId);

        return rs;
    }

    //增加节点
    private ResponseCode add_categoryDo(HttpServletRequest request) {

        ResponseCode rs = new ResponseCode();

        String parentId = request.getParameter("parentId");
        String categoryName = request.getParameter("categoryName");

        rs = cs.add_category(parentId, categoryName);

        return rs;
    }

    //修改品类名字
    private ResponseCode set_category_nameDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        String categoryId = request.getParameter("categoryId");
        String categoryName = request.getParameter("categoryName");

        rs = cs.setName(categoryId, categoryName);

        return rs;
    }
    
    //获取当前分类id及递归子节点
    private ResponseCode get_deep_categoryDo(HttpServletRequest request) {
        ResponseCode rs = new ResponseCode();

        String categoryId = request.getParameter("categoryId");


        rs = cs.getDeep(categoryId);

        return rs;
    }
}
