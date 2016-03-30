package com.nanosic.unittest;

import java.util.UUID;

/**
 * Created by Administrator on 2016/3/30.
 */
public class Person {
    private String name;
    private int age;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
