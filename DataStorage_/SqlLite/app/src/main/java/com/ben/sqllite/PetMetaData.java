package com.ben.sqllite;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2015/12/14 0014.
 */
/*
数据库元数据的定义
 */
    //加上final该类不可被继承
public final class PetMetaData {
    private PetMetaData(){}//构造方法私有化则对象不可在外部实例化
    //Dog表的定义
    public static abstract class DogTable implements BaseColumns{
        public static final String TABLE_NAME = "dog";
        public static final String NAME = "name";
        public static final String AGE = "age";

    }

}
