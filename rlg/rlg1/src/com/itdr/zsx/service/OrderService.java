package com.itdr.zsx.service;

import com.itdr.zsx.common.ResponseCode;
import com.itdr.zsx.dao.OrderDao;
import com.itdr.zsx.pojo.Order;

import java.util.List;

public class OrderService {
    OrderDao od= new OrderDao();

    public ResponseCode selectByNo(String orderNo) {
        ResponseCode rs =new ResponseCode();
        if (orderNo==null||orderNo.equals("")){
            rs.setStatus(1);
            rs.setMsg("发货失败");
            return rs;
        }
        Order order = null;
        order = od.selectByNo(orderNo);
        if (order==null){
            rs.setStatus(1);
            rs.setMsg("发货失败");
            return rs;
        }
        int  row = 0;
        row = od.sendGoods(orderNo);
        if (row==0){
            rs.setStatus(1);
            rs.setMsg("发货失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setMsg("发货成功");
        return rs;
    }
        //查询所有订单
    public ResponseCode selectAll(String pageSize, String pageNum) {
        if (pageSize == null || pageSize.equals("")) {
            pageSize = "10";
        }
        if (pageNum == null || pageNum.equals("")) {
            pageNum = "0";
        }

        List<Order> li = od.selectALL(pageSize, pageNum);

        ResponseCode rs = new ResponseCode();
        rs.setStatus(0);
        rs.setData(li);

        return rs;
    }

    public ResponseCode selectOne(String orderNo) {
        ResponseCode rs =new ResponseCode();
        if (orderNo==null||orderNo.equals("")){
            rs.setStatus(1);
            rs.setMsg("查询失败");
            return rs;
        }
        Order order = null;
        order = od.selectByNo(orderNo);
        if (order==null){
            rs.setStatus(1);
            rs.setMsg("查询失败");
            return rs;
        }
        rs.setStatus(0);
        rs.setData(order);
        return rs;
    }
}
