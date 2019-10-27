package com.wjjzst.juc.learn._15myDataSourcePool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @Author: Wjj
 * @Date: 2019/6/25 2:46
 * @desc:
 */
public class MyDataSource {

    private LinkedList<Connection> pool = new LinkedList<>();
    private static final int INIT_CONNECTION = 10;
    private static final String DRIVER_CLASS = "";
    private static final String USER = "root";
    private static final String PASSWORD = "Wzzst310@163.com";
    private static final String URL = "";


    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource() {
        try {
            for (int i = 0; i < 10; i++) {
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                pool.addLast(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnect() {
        Connection result = null;
        synchronized (pool) {
            while (pool.size() <= 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!pool.isEmpty()) {
            result = pool.removeFirst();
        }
        return null;
    }

    // 释放 就是放回到池子中的过程
    public void release(Connection conn) {
        if (conn != null) {
            synchronized (pool) {
                pool.addLast(conn);
                notifyAll();
            }
        }
    }
}
