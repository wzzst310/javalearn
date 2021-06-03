package com.wjjzst.ads.second_stage.learn._01sorting.cmp;

/**
 * @Author: Wjj
 * @Date: 2020/3/17 1:53 上午
 * @desc:
 */
public class A_Entity implements Comparable<A_Entity> {
    private int score;
    private int age;

    public A_Entity(int score, int age) {
        this.score = score;
        this.age = age;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(A_Entity o) {
        return age - o.age;
    }
}
