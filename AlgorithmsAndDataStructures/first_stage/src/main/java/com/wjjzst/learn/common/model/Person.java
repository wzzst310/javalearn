package com.wjjzst.learn.common.model;

import lombok.Data;

/**
 * @Author: Wjj
 * @Date: 2019/5/21 0:03
 * @desc:
 */
@Data
public class Person implements Comparable<Person> {

    public Person(String name, Integer age, boolean gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    private String name;
    private Integer age;
    private boolean gender;


    @Override
    public int compareTo(Person o) {
        return age - o.getAge();
    }
}
