package com.wjjzst.juc.learn.mockgo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangzhe
 * @create: 2021/5/19 8:30 下午
 * @Description
 */
public class Chan<T extends ChanMessage> {

    private List<T> lists = new ArrayList<>();

    private int size;

    public Chan(int size) {
        this.size = size;
    }


    public synchronized T take() {
        while (lists.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        T message = lists.remove(0);
        notifyAll();
        return message;
    }

    public synchronized void put(T message) {
        while (lists.size() >= size) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        lists.add(message);
        notifyAll();
    }


}

class ChanMessage<T> {
    private String type;
    private T data;

    public ChanMessage(String type, T data) {
        this.type = type;
        this.data = data;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
