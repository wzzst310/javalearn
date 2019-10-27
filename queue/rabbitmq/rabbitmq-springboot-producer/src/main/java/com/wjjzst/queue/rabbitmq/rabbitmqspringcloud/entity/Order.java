package com.wjjzst.queue.rabbitmq.rabbitmqspringcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data  //会复写equals 和hashCode方法
// 要实现Serializable 接口才能传输对象
public class Order implements Serializable {
    private String id;
    private String name;
}


