package com.ben.activity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/11/10 0010.
 */
public class Dog implements Parcelable {
    String name;
    int age;
    String type;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(type);
    }


    //对象创建器（解包）
    public static final Parcelable.Creator<Dog> CREATOR =
            new Parcelable.Creator<Dog>()
            {
                public Dog createFromParcel(Parcel in)
                {
                    Dog dog = new Dog();
                    dog.name = in.readString();
                    dog.age = in.readInt();
                    dog.type = in.readString();
                    return dog;
                }

                public Dog[] newArray(int size)
                {
                    return new Dog[size];
                }
            };
}
