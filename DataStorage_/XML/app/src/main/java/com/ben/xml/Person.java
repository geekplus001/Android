package com.ben.xml;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
public class Person {
     int id;
     String name;

     @Override
     public String toString() {
          return "Person{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", sex='" + sex + '\'' +
                  ", age=" + age +
                  ", tel='" + tel + '\'' +
                  '}';
     }

     String sex;
     int age;
     String tel;

}
