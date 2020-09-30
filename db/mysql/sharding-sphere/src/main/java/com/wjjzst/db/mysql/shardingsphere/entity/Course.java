package com.wjjzst.db.mysql.shardingsphere.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: Wjj
 * @Date: 2020/9/17 1:15 上午
 * @desc:
 */
@Data
public class Course {
    @TableId
    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;
}
