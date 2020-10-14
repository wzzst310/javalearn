package com.wjjzst.db.mysql.shardingsphere;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wjjzst.db.mysql.shardingsphere.entity.*;
import com.wjjzst.db.mysql.shardingsphere.mapper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: Wjj
 * @Date: 2020/9/17 1:17 上午
 * @desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingSphereApplicationTests {

    //注入mapper
    @Autowired
    private CourseMapper courseMapper;

    //注入user的mapper
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UdictMapper udictMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RecordMapper recordMapper;

    //======================测试shardingproxy==================
    //添加操作
    @Test
    public void addOrderDb() {
        for (int i = 0; i < 2000; i++) {
            Order order = new Order();
            //int orderId = (int) (Math.random() * 10000000);
            int userId = (int) (Math.random() * 10000000);
            order.setOrderId(i);
            order.setUserId(userId);
            order.setStatus(UUID.randomUUID().toString());
            orderMapper.insert(order);
        }
    }

    //查询操作
    @Test
    public void findOrderDb() {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        //设置userid值
        wrapper.eq("order_id", 1000);
        Order order = orderMapper.selectOne(wrapper);
        System.out.println(order);
    }


    //======================测试公共表===================
    //添加操作
    @Test
    public void addDict() {
        Udict udict = new Udict();
        udict.setUstatus("c");
        udict.setUvalue("已启用");
        udictMapper.insert(udict);
    }

    //删除操作
    @Test
    public void deleteDict() {
        QueryWrapper<Udict> wrapper = new QueryWrapper<>();
        //设置userid值
        wrapper.eq("dictid", 1309919818501120001L);
        udictMapper.delete(wrapper);
    }

    //======================测试垂直分库==================
    //添加操作
    @Test
    public void addUserDb() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("lucymary");
            user.setUstatus("a");
            userMapper.insert(user);
        }

    }

    //查询操作
    @Test
    public void findUserDb() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置userid值
        wrapper.eq("user_id", 1310271995765641218L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }


    //======================测试水平分库=====================
    //添加操作
    @Test
    public void addCourseDb() {
        Course course = new Course();
        course.setCname("javademo1");
        //分库根据user_id
        course.setUserId(110L);
        course.setCstatus("Normal1");
        courseMapper.insert(course);
    }

    //查询操作
    @Test
    public void findCourseDb() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        //设置userid值
        wrapper.eq("user_id", 110L);
        //设置cid值
        wrapper.eq("cid", 1308466072415977473L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

    //=======================测试水平分表===================
    //添加课程的方法
    @Test
    public void addCourse() {
        for (int i = 1; i <= 10; i++) {
            Course course = new Course();
            course.setCid((long) i);
            course.setCname("java" + i);
            course.setUserId(100L);
            course.setCstatus("Normal" + i);
            courseMapper.insert(course);
        }
    }

    //查询课程的方法
    @Test
    public void findCourse() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", 1307155339149357057L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

    //=======================测试按时间分表===================
    //添加课程的方法
    @Test
    public void addRecord() {
        for (int i = 1; i <= 10; i++) {
            Record record = new Record();
            record.setCid((long) i);
            record.setCname("java" + i);
            record.setUserId(100L);
            record.setCstatus("Normal" + i);
            record.setCreateTime(new Date());
            recordMapper.insert(record);
        }
    }

}
