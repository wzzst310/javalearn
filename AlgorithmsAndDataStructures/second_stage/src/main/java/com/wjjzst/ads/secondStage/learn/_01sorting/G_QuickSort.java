package com.wjjzst.ads.secondStage.learn._01sorting;

/**
 * @Author: Wjj
 * @Date: 2020/4/20 10:16 下午
 * @desc: 快速排序
 */
public class G_QuickSort<E extends Comparable<E>> extends A_AbstractSort<E> {
    @Override
    protected void sort() {
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        // 确定轴点
        int pivot = pivotIndex(begin, end);
        // 对子序列进行快速排序
        sort(begin, pivot);
        sort(pivot + 1, end);
    }

    /**
     * @return 最终轴点位置
     */
    private int pivotIndex(int begin, int end) {
        // 随机选择一个位置元素和begin进行交换 随机选择轴点  避免最坏情况
        swap(begin, begin + (int) Math.random() * (end - begin));
        // 保存begin位置的元素
        E pivotValue = array[begin];
        // end-1 保证end是在指向数组中的元素
        end--;
        while (begin < end) {
            // 从右往左扫描
            while (begin < end) {
                // 当右侧的元素比轴点元素大时 只需要把end指针往左移
                // 为什么不用<=0 是为了保证切割出来的子序列相同元素不再pivot元素的同一侧子序列极度不均匀 致使最坏情况发生
                if (cmp(pivotValue, array[end]) < 0) {
                    end--;
                } else { // 当右侧的元素比轴点元素小或等于时 需要把右侧元素放在左侧begin位置 并且左侧begin+1 往右移一位 转换成 从左往右扫描
                    array[begin++] = array[end];
                    break;
                }
            }
            // 从左往右扫描
            while (begin < end) {
                // 当左侧元素比轴点元素小时 只需要把begin指针往右移
                if (cmp(pivotValue, array[begin]) > 0) {
                    begin++;
                } else { // 当左侧的元素比轴点元素大或等于时 需要把左侧元素放在右侧end位置 并且右侧end-1 往左移一位  转换成 从右往左扫描
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        // begin=end= pivot 位置  并将原本的begin位置的值赋上
        array[begin] = pivotValue;
        return begin;
    }
}
