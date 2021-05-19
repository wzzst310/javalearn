package com.wjjzst.juc.learn.mockgo;

/**
 * @author: wangzhe
 * @create: 2021/5/19 8:22 下午
 * @Description
 */
class ChanMessageNoCap<T> {
    private String type;
    private T data;

    public ChanMessageNoCap(String type, T data) {
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

public class ChanNoCap<T extends ChanMessageNoCap> {
    private T message;

    private boolean empty = true;

    public synchronized T take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        empty = true;
        notifyAll();
        return message;
    }

    public synchronized void put(T message) {
        while (!empty) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }


    public static void main(String[] args) {
        ChanNoCap<ChanMessageNoCap<Integer>> chanMessageChanNoCap = new ChanNoCap<>();
        new Thread(() -> {
            chanMessageChanNoCap.put(new ChanMessageNoCap<>("timeout", new Integer(1)));
        }).start();
        new Thread(() -> {
            ChanMessageNoCap<Integer> chanMessageNoCap = chanMessageChanNoCap.take();
            switch (chanMessageNoCap.getType()) {
                case "timeout":
                    System.out.println(chanMessageNoCap.getData());
                    break;
                default:
                    break;
            }
        }).start();
    }
}

