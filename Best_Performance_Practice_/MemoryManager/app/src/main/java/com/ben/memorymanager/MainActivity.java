package com.ben.memorymanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    int s = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //性能较低的集合
        HashMap<Integer,String> map = new HashMap<>();
        map.put(1,"ben");
        map.put(2,"doubi");
        map.put(3,"hehe");

        //性能更高的集合
        SparseArray<String> array = new SparseArray<>();
        array.put(1,"ben");
        array.put(2,"doubi");
        array.put(3,"hehe");
        //SparseBooleanArray   LongSparseArray

        int[] nums = {1,2,3,4,5,6};

        //JDK1.5之后支持  在没有JIT（Just In Time Compiler）的设备上运行时最快的，有JIT的设备上运行效率和下边方法差不多
        for(int s:nums)
        {
            System.out.println(s);
        }

        for(int i=0,t=nums.length;i<t;i++)//块
        {
            System.out.println(nums[i]);
        }
    }

    public void method()
    {
        int t = s;
    }
}
