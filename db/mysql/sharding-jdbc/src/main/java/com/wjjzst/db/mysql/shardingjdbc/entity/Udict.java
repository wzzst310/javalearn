package com.wjjzst.db.mysql.shardingjdbc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: Wjj
 * @Date: 2020/9/17 1:15 上午
 * @desc:
 */
@Data
@TableName(value = "t_udict")
public class Udict {
    @TableId
    private Long dictid;
    private String ustatus;
    private String uvalue;
}
