package com.itdr.zsx.dao;

import com.itdr.zsx.pojo.Order;
import com.itdr.zsx.utils.PoolUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class OrderDao {
    //根据订单编号查订单
    public Order selectByNo(String orderNo) {
        Long oNo = Long.parseLong(orderNo);
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from orders where orderNo = ? ";
        Order o = new Order();
        try {
            o = qr.query(sql,new BeanHandler<Order>(Order.class),oNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return o;
    }
        //发送货物
    public int sendGoods(String orderNo) {
        Long oNo = Long.parseLong(orderNo);
        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "update  orders set statusDesc = '已支付',paymentTime = current_timestamp,sendTime= current_timestamp where orderNo = ?";
        int row = 0;
        try {
            row = qr.update(sql,oNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return row;
    }
    //获取订单列表
    public List<Order> selectALL(String pageSize, String pageNum) {
        Integer ps = Integer.parseInt(pageSize);
        Integer pn = Integer.parseInt(pageNum);

        QueryRunner qr = new QueryRunner(PoolUtil.getcom());
        String sql = "select * from orders limit ? , ?";
        List<Order> li = null;
        try {
            li = qr.query(sql, new BeanListHandler<Order>(Order.class), pn, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return li;
    }
}
