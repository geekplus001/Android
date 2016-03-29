package com.ben.sqlitegamedemo;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import db.DataBaseAdapter;
import entity.GamePlayer;
import fragments.AddFragment;
import fragments.GamePlayerFragment;
import fragments.UpdateFragment;

public class MainActivity extends AppCompatActivity implements AddFragment.AddFragmentListener, GamePlayerFragment.GamePlayerFragmentListener, UpdateFragment.UpdateFragmentListener{

    private GamePlayerFragment gamePlayerFragment;
    private UpdateFragment updateFragment;
    private DataBaseAdapter dataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseAdapter = new DataBaseAdapter(this);
        showGamePlayerFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //右上角加号
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.add:
                AddFragment createGamePlayerFragment = AddFragment.newInstance();
                createGamePlayerFragment.show(getFragmentManager(),null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //显示所有玩家信息的Fragment
    @Override
    public void showGamePlayerFragment() {
        gamePlayerFragment = GamePlayerFragment.newInstance();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_layout,gamePlayerFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    //更新玩家信息后再显示所有玩家信息
    @Override
    public void showUpdateFragment(int id) {
        updateFragment = UpdateFragment.newInstance(id);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_layout,updateFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void delete(int id) {
        dataBaseAdapter.delete(id);
        gamePlayerFragment.changeData();
    }

    @Override
    public ArrayList<GamePlayer> findAll() {
        return dataBaseAdapter.findAll();
    }

    @Override
    public void add(GamePlayer gamePlayer) {
        dataBaseAdapter.add(gamePlayer);
        gamePlayerFragment.changeData();
    }

    @Override
    public void update(GamePlayer gamePlayer) {
        dataBaseAdapter.update(gamePlayer);
        gamePlayerFragment.changeData();
    }

    @Override
    public GamePlayer findById(int id) {

        return  dataBaseAdapter.findById(id);
    }


    //判断是否退出程序
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(getFragmentManager().getBackStackEntryCount()==1)
            {
                finish();
                return true;
            }
            else
            {
                getFragmentManager().popBackStack();
            }
        }
        return super.onKeyDown(keyCode,event);
    }
}
