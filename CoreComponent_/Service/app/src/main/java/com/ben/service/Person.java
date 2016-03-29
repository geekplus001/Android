package com.ben.service;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/11/20 0020.
 */
public class Person implements Parcelable{
    String name;
    String work;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", work='" + work + '\'' +
                '}';
    }

    public static final Parcelable.Creator<Person> CREATOR =
            new Parcelable.Creator<Person>()
            {

                public Person createFromParcel(Parcel in)
                {
                    Person p = new Person();
                    p.name = in.readString();
                    p.work = in.readString();
                    return p;
                }
                public Person[] newArray(int size)
                {
                    return  new Person[size];
                }
            };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(work);
    }
}
