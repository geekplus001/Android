// ICat.aidl
package com.ben.service;
import com.ben.service.Person;

// Declare any non-default types here with import statements

interface ICat {

    Person getPerson();
    void setName(String name);

    String desc();
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
