package com.ben.loaders;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2015/12/17 0017.
 */
public final class PersonMetaData {
    private PersonMetaData()
    {}

    public static abstract class Person implements BaseColumns{
        public static final String TABLE_NAME = "person";
        public static final String NAME = "name";
        public static final String AGE = "age";
    }
}
