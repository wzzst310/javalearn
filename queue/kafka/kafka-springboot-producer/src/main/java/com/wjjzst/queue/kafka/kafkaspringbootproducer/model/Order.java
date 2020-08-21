package com.wjjzst.queue.kafka.kafkaspringbootproducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@Data  //会复写equals 和hashCode方法
public class Order implements Serializable {
    private String id;
    private String name;

    public Order() {
        this.id = UUID.randomUUID().toString();
        this.name = UUID.randomUUID().toString();
    }
}


