package com.itdr.zsx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDemo {
    @Test
    public void test1() throws SQLException {
        ComboPooledDataSource co = new ComboPooledDataSource();
        Connection connection = co.getConnection();
        String sql = "select * from users";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(2));
        }

    }

    @Test
    public void test() {
        String x = "fmn";
        String s = x.toUpperCase();
        System.out.println(x);
        System.out.println(s);
    }

    @Test
    public void test2() {
        int a = 16;
        for (a = 16; a < 20; a++) {
            System.out.print(a);
            digui(a);

        }


    }

    private List li = new ArrayList();

    private void digui(int d) {
        int b = d;
        if (d == 1) {
            System.out.println("可以");
            return;
        }

        //平方值
        d = 0;
        while (b != 0) {
            int c = b % 10;
            d += c * c;
            b /= 10;
        }
        li.add(d);
        for (int i = 0; i < li.size() - 1; i++) {
            if (d == (int) li.get(i)) {
                System.out.println("不可以");
                return;
            }
        }
        digui(d);

    }
}
