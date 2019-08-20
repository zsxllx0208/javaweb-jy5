package com.itdr.zsx.dao;

import com.itdr.zsx.pojo.Product;
import com.itdr.zsx.utils.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    //查询商品列表
    public List<Product> selectALL(String pageSize, String pageNum) {
        Integer ps = Integer.parseInt(pageSize);
        Integer pn = Integer.parseInt(pageNum);

        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from product limit ? , ?";
        List<Product> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Product>(Product.class), pn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;
    }

    //根据ID,名字查找商品
    public List<Product> selectByIdName(String pageSize, String pageNum, String productName, String productId) {
        Integer ps = Integer.parseInt(pageSize);
        Integer pn = Integer.parseInt(pageNum);
        Integer pid = Integer.parseInt(productId);
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from product where id = ? and pname = ? limit ? , ?";
        List<Product> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Product>(Product.class), pid, productName, pn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;

    }

    //根据ID查找商品
    public Product selectById(String productId) {
        Integer pid = Integer.parseInt(productId);
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from product where id = ? ";
        Product p = null;
        try {
            p = qr.query(sql, new BeanHandler<Product>(Product.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    //商品上下架
    public int upDown(String productId, String status) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "update  product set status = ? where id = ?";
        int row = 0;
        int pId = Integer.parseInt(productId);
        int ps = Integer.parseInt(status);
        try {
            row = qr.update(sql, ps, pId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;

    }

    //根据名字查找商品
    public Product selectByName(String name) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from product where pname = ? ";
        Product p = null;
        try {
            p = qr.query(sql, new BeanHandler<Product>(Product.class), name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    //新增商品
    public int newProduct(String categoryId, String name, String subtitle, String mainImage, String status, String price) {
        Integer cateId = Integer.parseInt(categoryId);
        Integer stats = Integer.parseInt(status);
        Double pp = Double.parseDouble(price);
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "insert into product(categoryId,pname,subtitle,mainImage,status," +
                "price) values( ? , ? , ? , ? , ? , ? )";
        int row = 0;
        try {
            row = qr.update(sql, cateId, name, subtitle, mainImage, stats, pp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //更新商品
    public int upProduct(String categoryId, String name, String subtitle, String mainImage, String status, String price) {
        Integer cateId = Integer.parseInt(categoryId);
        Integer stats = Integer.parseInt(status);
        Double pp = Double.parseDouble(price);
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "update  product set categoryId = ? ,subtitle= ? ,mainImage= ? " +
                ",status = ? ,price = ? where pname = ? ";
        int row = 0;
        try {
            row = qr.update(sql, cateId, subtitle, mainImage, stats, pp,name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

}
