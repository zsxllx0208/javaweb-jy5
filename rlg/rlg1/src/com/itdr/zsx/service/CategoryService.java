package com.itdr.zsx.service;

import com.itdr.zsx.common.ResponseCode;
import com.itdr.zsx.dao.CategoryDao;
import com.itdr.zsx.pojo.Category;

import java.util.List;

public class CategoryService {
    CategoryDao cd = new CategoryDao();

    public ResponseCode selectBycategoryId(String categoryId) {
        ResponseCode rs = new ResponseCode();

        if (categoryId == null || categoryId.equals("")) {
            rs.setStatus(1);
            rs.setMsg("查询失败");
            return rs;
        }
        List<Category> li = null;
        li = cd.selectBygoryId(categoryId);
        if (li == null) {
            rs.setStatus(1);
            rs.setMsg("查询失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(li);
        return rs;

    }

    public ResponseCode add_category(String parentId, String categoryName) {
        ResponseCode rs = new ResponseCode();
        if (parentId == null || parentId.equals("")) {
            parentId = "0";
        }
        if (categoryName == null || categoryName.equals("")) {
            rs.setStatus(1);
            rs.setMsg("增加失败");
            return rs;
        }
        int row = 0;
        row = cd.add_category(parentId, categoryName);
        if (row == 0) {
            rs.setStatus(1);
            rs.setMsg("增加失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setMsg("增加成功");
        return rs;

    }

    //修改品类名字
    public ResponseCode setName(String categoryId, String categoryName) {
        ResponseCode rs = new ResponseCode();
        if (categoryId == null || categoryId.equals("") || categoryName == null || categoryName.equals("")) {
            rs.setStatus(1);
            rs.setMsg("更新品类名字失败");
            return rs;
        }
        int row = 0;
        row = cd.setName(categoryId, categoryName);

        if (row == 0) {
            rs.setStatus(1);
            rs.setMsg("更新品类名字失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setMsg("更新品类名字成功");
        return rs;
    }

    public ResponseCode getDeep(String categoryId) {
        ResponseCode rs = new ResponseCode();
        if (categoryId == null || categoryId.equals("")) {
            rs.setStatus(1);
            rs.setMsg("获取失败");
            return rs;
        }
        Integer pid = Integer.parseInt(categoryId);
        List<Integer> li = null;
        li = cd.getDeep(pid);
        if (li==null){
            rs.setStatus(1);
            rs.setMsg("获取失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(li);
        return rs;
    }
}
