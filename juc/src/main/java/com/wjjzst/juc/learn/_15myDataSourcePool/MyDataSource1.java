package com.wjjzst.juc.learn._15myDataSourcePool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Wjj
 * @Date: 2019/6/25 2:46
 * @desc:
 */
public class MyDataSource1 {

    private LinkedList<Connection> pool = new LinkedList<>();
    private static final int INIT_CONNECTION = 10;
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "Wzzst310@163.com";
    private static final String URL = "";
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource1() {
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
        lock.lock();
        try {
            while (pool.size() <= 0) {
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!pool.isEmpty()) {
                result = pool.removeFirst();
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

    // 释放 就是放回到池子中的过程
    public void release(Connection conn) {

        if (conn != null) {
            lock.lock();
            try {
                pool.addLast(conn);
                c1.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
