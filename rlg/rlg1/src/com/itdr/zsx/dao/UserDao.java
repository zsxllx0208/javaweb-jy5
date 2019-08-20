package com.itdr.zsx.dao;

import com.itdr.zsx.pojo.Users;
import com.itdr.zsx.utils.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    //查找所有用户
    public List<Users> selectALL(String pageSize, String pageNum) {
        Integer ps = Integer.parseInt(pageSize);
        Integer pn = Integer.parseInt(pageNum);

        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from users limit ? , ?";
        List<Users> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Users>(Users.class),pn,ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;

    }
    //根据用户名密码查找一个用户
    public Users selectOne(String username, String password) {
        QueryRunner qr = new QueryRunner( PoolUtil.getcom());
        String sql = "select * from users where uname = ? and psd = ?";
//        Integer psd = Integer.parseInt(password);
        Users u = null;
        try {
            u = qr.query(sql, new BeanHandler<Users>(Users.class),username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }
    //根据id查找一个用户
    public Users selectOne(String uid) {
        QueryRunner qr = new QueryRunner( PoolUtil.getcom());
        String sql = "select * from users where id = ?";
        Users u = null;
        Integer id = Integer.parseInt(uid);
        try {
            u = qr.query(sql, new BeanHandler<Users>(Users.class),id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }
    //根据id禁用一个用户
    public int updataByUid(String uid) {
        QueryRunner qr = new QueryRunner( PoolUtil.getcom());
        String sql = "update  users set stats = 1 where id = ?";
        int row =0;
        Integer id = Integer.parseInt(uid);
        try {
            row = qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }
    //根据id解禁一个用户
    public int updataByUid2(String uid) {
        QueryRunner qr = new QueryRunner( PoolUtil.getcom());
        String sql = "update  users set stats = 0 where id = ?";
        int row =0;
        Integer id = Integer.parseInt(uid);
        try {
            row = qr.update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }


    public List<Users> selectManages(String pageSize, String pageNum) {

        Integer ps = Integer.parseInt(pageSize);
        Integer pn = Integer.parseInt(pageNum);

        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from users where type=1 limit ? ,? ";
        List<Users> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Users>(Users.class),pn,ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;

    }
}
