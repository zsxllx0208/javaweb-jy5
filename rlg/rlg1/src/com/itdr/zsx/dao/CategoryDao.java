package com.itdr.zsx.dao;

import com.itdr.zsx.pojo.Category;
import com.itdr.zsx.utils.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public List<Category> selectBygoryId(String categoryId) {
        Integer pid = Integer.parseInt(categoryId);

        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from category where parentId = ? ";
        List<Category> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Category>(Category.class), pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;
    }

    public int add_category(String parentId, String categoryName) {
        Integer pid = Integer.parseInt(parentId);
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "insert into category(parentId,name) values( ? , ? )";
        int row = 0;
        try {
            row = qr.update(sql, pid, categoryName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //修改品类名字
    public int setName(String categoryId, String categoryName) {
        Integer cid = Integer.parseInt(categoryId);

        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "update  category set name = ? where id = ? ";
        int row = 0;
        try {
            row = qr.update(sql, categoryName, cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    //获取当前分类id及递归子节点
    List<Integer> li = new ArrayList<Integer>();

    public List<Integer> getDeep(Integer pid) {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());

        String sql = "select id from category where parentId = ?";
        try {
            li = qr.query(sql, new BeanListHandler<Integer>(Integer.class), pid);
            getMore(li);
        } catch (SQLException e) {
            e.printStackTrace();
        }




        return li;
    }

    private void getMore(List<Integer> list) throws SQLException {
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select id from category where parentId = ?";
        List<Integer> list2 = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            list2 = qr.query(sql, new BeanListHandler<Integer>(Integer.class), list.get(i));
            if (list2.size() == 0) {
                continue;
            } else {
                for (int j = 0; j < list2.size(); j++) {
                    li.add(list2.get(i));
                }
            }
        }

    }
}
