package com.ben.sqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DataBaseAdapter dataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseAdapter = new DataBaseAdapter(this);
    }

    public void addClick(View v)
    {
        Dog dog = new Dog("doggy",4);
        dataBaseAdapter.add(dog);
    }
    public void deleteClick(View v)
    {
        dataBaseAdapter.delete(1);
    }
    public void updateClick(View v)
    {
        Dog dog = new Dog(1,"wangwang",5);
        dataBaseAdapter.update(dog);
    }
    public void findByIdClick(View v)
    {
        Dog dog = dataBaseAdapter.findDogById(1);
        System.out.println(dog);
    }
    public void findAllClick(View v)
    {
        ArrayList<Dog> dogs = dataBaseAdapter.findAllDog();
        for(Dog dog:dogs)
        {
            System.out.println(dog);
        }
    }

}
