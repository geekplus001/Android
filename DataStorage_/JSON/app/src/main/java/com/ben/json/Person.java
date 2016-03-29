package com.ben.json;

/**
 * Created by Administrator on 2015/12/3 0003.
 */
public class Person {
    String firstName;
    String lastName;
    String email;

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
