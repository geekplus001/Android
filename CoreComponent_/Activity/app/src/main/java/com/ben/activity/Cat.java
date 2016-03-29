package com.ben.activity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/10 0010.
 */
public class Cat implements Serializable{
     String name;
     int age;
     String type;

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }
}
