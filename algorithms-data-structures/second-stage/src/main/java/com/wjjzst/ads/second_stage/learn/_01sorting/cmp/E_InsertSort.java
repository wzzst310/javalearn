package com.wjjzst.ads.second_stage.learn._01sorting.cmp;

/**
 * @Author: Wjj
 * @Date: 2020/3/18 12:55 上午
 * @desc: 插入排序
 */
public class E_InsertSort<E extends Comparable<E>> extends A_AbstractSort<E> {
    @Override
    protected void sort() {
        teacherSort();
    }

    private void sort1() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                swap(cur, cur - 1);
                cur--;
            }
        }
    }

    //优化一 交换转为挪动 只把大于当前的数往尾部移
    private void sort2() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            E element = array[cur];
            // 开始选中的element 与前面的比较
            while (cur > 0 && cmp(element, array[cur - 1]) < 0) {
                // swap(cur, cur - 1);
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = element;
        }
    }

    //优化二 由于前面的数据都是有序的，所以可以采用二分搜索法找到  选中的element需要插入 的位置
    private void sort3() {
        for (int begin = 1; begin < array.length; begin++) {
            E element = array[begin];
            int left = 0;
            int right = begin;
            int mid = 0;
            while (left < right) {
                mid = (left + right) >> 1;
                if (cmp(mid, begin) < 0) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            for (int i = begin; i > left; i--) {
                array[i] = array[i - 1];
            }
            array[mid] = element;
        }
    }

    private void teacherSort() {
        for (int i = 1; i < array.length; i++) {
            insert(i, search(i));
        }
    }

    private void insert(int source, int dest) {
        E element = array[source];
        for (int i = source; i > dest; i--) {
            array[i] = array[i - 1];
        }
        array[dest] = element;
    }

    private int search(int index) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(index, mid) < 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }

    public static int search_1(int[] array, int v) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) {
                end = mid;
            } else if (v > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    /*
    private void mySort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            E element = array[cur];
            // 自己的错误代码    相比较的应该是最开始选中的element 与前面的逐渐比较 cmp(cur, cur - 1)
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                // swap(cur, cur - 1);
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = element;
        }
    }*/

}
