package com.wjjzst.ads.firststage.learn.set;


import com.wjjzst.ads.firststage.learn.common.List;
import com.wjjzst.ads.firststage.learn.linkedlist.LinkedList;

/**
 * @Author: Wjj
 * @Date: 2019/5/19 19:58
 * @desc:
 */
public class ListSet<E> implements Set<E> {
    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        int index = list.indexOf(element);
        if (index == List.ELEMENT_NOE_FOUND) {
            list.add(element);
        } else {
            list.set(index, element);
        }
    }

    @Override
    public void remove(E element) {
        int index = list.indexOf(element);
        if (index != List.ELEMENT_NOE_FOUND) {
            list.remove(element);
        }
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (visitor.visit(list.get(i))) {
                return;
            }
        }

    }
}
