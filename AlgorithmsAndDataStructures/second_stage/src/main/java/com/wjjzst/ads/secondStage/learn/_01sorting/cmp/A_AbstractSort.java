package com.wjjzst.ads.secondStage.learn._01sorting.cmp;

import com.wjjzst.ads.secondStage.learn._01sorting.I_CountSort;

import java.text.DecimalFormat;

public abstract class A_AbstractSort<E extends Comparable<E>> implements Comparable<A_AbstractSort> {
    public E[] array;
    private int cmpCount;
    private int swapCount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(E[] array) {
        if (array == null || array.length < 2) return;

        this.array = array;

        long begin = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begin;
    }

    @Override
    public int compareTo(A_AbstractSort o) {
        int result = (int) (time - o.time);
        if (result != 0) return result;

        result = cmpCount - o.cmpCount;
        if (result != 0) return result;

        return swapCount - o.swapCount;
    }

    protected abstract void sort();

    /*
     * 返回值等于0，代表 array[i1] == array[i2]
     * 返回值小于0，代表 array[i1] < array[i2]
     * 返回值大于0，代表 array[i1] > array[i2]
     */
    protected int cmp(int i1, int i2) {
        cmpCount++;
        return array[i1].compareTo(array[i2]);
    }

    protected int cmp(E v1, E v2) {
        cmpCount++;
        return v1.compareTo(v2);
    }

    protected void swap(int i1, int i2) {
        swapCount++;
        E tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        String stableStr = "稳定性: " + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";

    }

    private boolean isStable() {
        if (this instanceof H_ShellSort) {
            return false;
        }
        if (this instanceof I_CountSort) {
            return true;
        }
        A_Entity[] entities = new A_Entity[20];
        for (int i = 0; i < entities.length; i++) {
            entities[i] = new A_Entity(i * 10, 10);
        }
        sort((E[]) entities);
        for (int i = 1; i < entities.length; i++) {
            int score = entities[i].getScore();
            int preScore = entities[i - 1].getScore();
            if (score != preScore + 10) {
                return false;
            }
        }
        return true;
    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }
}
