package com.wjjzst.db.mysql.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Wjj
 * @Date: 2020/10/1 12:36 上午
 * @desc:
 */
@Data
@TableName(value = "t_order")
public class Order {
    @TableId
    private Integer orderId;
    private Integer userId;
    private String status;
}
