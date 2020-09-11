package com.wjjzst.common.enums;

/**
 * @author: Wjj
 * @create: 2020/9/11 7:28 下午
 * @Description
 */
public enum ComplexEnum {
    COMPLEX_ENUM_A(11, "A"),
    COMPLEX_ENUM_B(12,"B")
    ;

    ComplexEnum(int age, String hobby) {
        this.age = age;
        this.hobby = hobby;
    }

    private int age;
    private String hobby;

}
