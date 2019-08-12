package com.itdr.zsx.service;

import com.itdr.zsx.common.Const;
import com.itdr.zsx.common.ResponseCode;
import com.itdr.zsx.dao.UserDao;
import com.itdr.zsx.pojo.Users;

import java.util.List;

public class UserService {
    private UserDao ud = new UserDao();
    //获取用户列表
    public ResponseCode selectAll(String pageSize, String pageNum) {
        if (pageSize == null||pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null||pageNum.equals("")) {
            pageNum = "1";
        }

        List<Users> li = ud.selectALL(pageSize, pageNum);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);

        return rs;
    }

    //用户登录
    public ResponseCode selectOne(String username, String password) {
        ResponseCode rs = new ResponseCode();
        //非空判断
        if (username == null || username.equals("") || password == null || password.equals("")) {
            rs.setStatus(1);
            rs.setMsg("账号或密码错误");
            return rs;
        }
        //查找是否有这样一个用户
        Users u = ud.selectOne(username,password);

        if (u==null){
            rs.setStatus(1);
            rs.setMsg("账号或密码错误");
            return rs;
        }
        //权限判断
        if(u.getType()!=1){
            rs.setStatus(2);
            rs.setMsg("没有操作权限");
            return rs;
        }

        //查找是否有这样一个用户



        rs.setStatus(0);
        rs.setData(u);
        return rs;

    }

    //用户禁用
    public ResponseCode selectOne(String uid) {
        ResponseCode rs = new ResponseCode();
        //非空判断
        if (uid == null || uid.equals("")) {
            rs.setStatus(Const.USER_PARAMETER_CODE);
            rs.setMsg(Const.USER_PARAMETER_MSG);
            return rs;
        }
        //查找是否有这样一个用户

        Users u = ud.selectOne(uid);

        if (u==null){
            rs.setStatus(Const.USER_NO_CODE);
            rs.setMsg(Const.USER_NO_MSG);
            return rs;
        }
        //用户禁用情况判断
        if(u.getStats()==1){
            rs.setStatus(Const.USER_DISABLE_CODE);
            rs.setMsg(Const.USER_DISABLE_MSG);
            return rs;
        }

        //禁用用户

        int row =ud.updataByUid(uid);

        if (row<=0){
            rs.setStatus(106);
            rs.setMsg("禁用失败");
            return rs;
        }

        rs.setStatus(0);
        rs.setMsg("禁用成功");
        return rs;

    }

}
