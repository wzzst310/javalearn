package com.wjjzst.db.mysql.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Wjj
 * @Date: 2020/9/17 1:15 上午
 * @desc:
 */
@Data
@TableName(value = "t_user")  //指定对应表
public class User {
    @TableId
    private Long userId;
    private String username;
    private String ustatus;
    private String point;   // bug bug
}
