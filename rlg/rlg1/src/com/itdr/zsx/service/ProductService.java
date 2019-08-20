package com.itdr.zsx.service;

import com.itdr.zsx.common.Const;
import com.itdr.zsx.common.ResponseCode;
import com.itdr.zsx.dao.ProductDao;
import com.itdr.zsx.pojo.Product;
import com.itdr.zsx.pojo.Users;

import java.util.List;

public class ProductService {
    ProductDao pd = new ProductDao();

    //获取商品列表
    public ResponseCode selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "0";
        }

        List<Product> li = pd.selectALL(pageSize, pageNum);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);

        return rs;
    }

    ////根据商品名称和ID查询商品
    public ResponseCode selectByIdName(String pageSize, String pageNum, String productName, String productId) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "0";
        }

        List<Product> li = pd.selectByIdName(pageSize, pageNum, productName, productId);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);

        return rs;


    }

    //根据ID查询商品
    public ResponseCode selectById(String productId) {

        ResponseCode rs = new ResponseCode();

        Product p = pd.selectById(productId);

        if (p == null) {
            rs.setStatus(1);
            rs.setMsg("查询失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(p);

        return rs;

    }

    //根据ID商品上下架
    public ResponseCode upDown(String productId, String status) {
        ResponseCode rs = new ResponseCode();
        //非空判断
        if (productId == null || productId.equals("") || status == null || status.equals("")) {
            rs.setStatus(1);
            rs.setMsg("修改产品状态失败");
            return rs;
        }
        //查找是否有这样一个用户

        Product p = pd.selectById(productId);

        if (p == null) {
            rs.setStatus(1);
            rs.setMsg("修改产品状态失败");
            return rs;
        }
        Integer ps = Integer.parseInt(status);
        if (p.getStatus().equals(ps)) {
            rs.setStatus(1);
            rs.setMsg("修改产品状态失败");
            return rs;
        }
        int row = 0;
        row = pd.upDown(productId, status);
        if (row < 1) {
            rs.setStatus(1);
            rs.setMsg("修改产品状态失败");
            return rs;
        }

        rs.setStatus(0);
        rs.setMsg("修改产品状态成功");
        return rs;

    }

    //产品更新OR新增
    public ResponseCode saveDo(String categoryId, String name, String subtitle, String mainImage, String status, String price) {
        ResponseCode rs = new ResponseCode();
        //非空判断
        if (categoryId == null || categoryId.equals("") || name == null || name.equals("") || status == null || status.equals("") ||
                price == null || price.equals("")) {
            rs.setStatus(1);
            rs.setMsg("更新产品失败");
            return rs;
        }
        //查找是否有名称相同的商品
        Product p = pd.selectByName(name);
        if (p == null) {
            int row = pd.newProduct(categoryId, name, subtitle, mainImage, status, price);
            if (row != 0) {
                rs.setStatus(0);
                rs.setMsg("新增产品成功");
                return rs;
            }
        } else {
            int row = pd.upProduct(categoryId, name, subtitle, mainImage, status, price);
            if (row != 0) {
                rs.setStatus(0);
                rs.setMsg("更新产品成功");
                return rs;
            }


        }
        rs.setStatus(1);
        rs.setMsg("产品更新失败");
        return rs;
    }
}
