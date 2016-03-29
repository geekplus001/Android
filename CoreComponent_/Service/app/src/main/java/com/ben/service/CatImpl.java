package com.ben.service;

import android.os.RemoteException;

/*
业务接口的具体实现类
 */
/**
 * Created by Administrator on 2015/11/19 0019.
 */
public class CatImpl extends ICat.Stub {
    private String name;

    @Override
    public Person getPerson() throws RemoteException {
        Person p = new Person();
        p.name = "小次郎";
        p.work = "强盗";
        return p;
    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String desc() throws RemoteException {
        return "hello my name is "+name+",I am a cat.";
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }
}
