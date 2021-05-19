package com.wjjzst.juc.learn.mockgo;

/**
 * @author: wangzhe
 * @create: 2021/5/19 8:32 下午
 * @Description
 */
public class Test {
    static void go(Runnable runnable) {
        new Thread(runnable).start();
    }
    public static void main(String[] args) {
        Chan<ChanMessage<Integer>> chanMessageChan = new Chan<>(1);
        go(() -> {
            chanMessageChan.put(new ChanMessage<>("success", new Integer(1)));
            chanMessageChan.put(new ChanMessage<>("success", new Integer(2)));
        });
        go(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chanMessageChan.put(new ChanMessage<>("timeout", new Integer(0)));
        });
        go(() -> {
            while (true) {
                ChanMessage<Integer> chanMessage = chanMessageChan.take();
                switch (chanMessage.getType()) {
                    case "success":
                        System.out.println(chanMessage.getData());
                        break;
                    case "timeout":
                        return;
                    default:
                        break;

                }
            }
        });
    }

    public static void main1(String[] args) {
        Chan<ChanMessage<Integer>> chanMessageChan = new Chan<>(1);

        new Thread(() -> {
            chanMessageChan.put(new ChanMessage<>("success", new Integer(1)));

            chanMessageChan.put(new ChanMessage<>("success", new Integer(2)));
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chanMessageChan.put(new ChanMessage<>("timeout", new Integer(0)));
        }).start();
        new Thread(() -> {
            while (true) {
                ChanMessage<Integer> chanMessage = chanMessageChan.take();
                switch (chanMessage.getType()) {
                    case "success":
                        System.out.println(chanMessage.getData());
                        break;
                    case "timeout":
                        return;
                    default:
                        break;

                }
            }
        }).start();
    }
}
