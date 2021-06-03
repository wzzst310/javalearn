package com.wjjzst.ads.firststage.exam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * @Author: Wjj
 * @Date: 2019/6/14 1:33
 * @desc:
 */

@Data
public class Category {
    private Integer id;
    private Integer parentId;
    private List<Category> children;

    public Category(Integer id, Integer parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    public static void main(String[] args) {
        Collection<Category> categories = new ArrayList<>(); //
        categories.add(new Category(1, 0));
        categories.add(new Category(2, 2));
        categories.add(new Category(3, 1));
        categories.add(new Category(4, 1));
        categories.add(new Category(5, 4));
        Category top = new Category(0, null);
        categories.add(top);
        // 根 为0  top
        Queue<Category> queue = new LinkedList<>();
        queue.offer(top);
        while (!queue.isEmpty()) {
            Category poll = queue.poll();
            List<Category> children = getChildrenById(poll.getId(), categories);
            queue.addAll(children);
            poll.setChildren(children);
        }
        System.out.println(top);
    }

    private static List<Category> getChildrenById(Integer id, Collection<Category> categories) {
        return categories.stream().filter(c -> id.equals(c.getParentId())).collect(Collectors.toList()); //待优化
    }
}
