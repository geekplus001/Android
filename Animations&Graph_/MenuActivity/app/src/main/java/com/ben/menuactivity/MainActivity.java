package com.ben.menuactivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int[] res={
      R.id.imageView,
      R.id.imageView2,
      R.id.imageView3,
      R.id.imageView4
    };
    private ArrayList<ImageView> list = new ArrayList<>();
    private boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0;i<res.length;i++)
        {
            ImageView imageView = (ImageView) findViewById(res[i]);
            imageView.setOnClickListener(this);
            list.add(imageView);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageView:
                if(isOpen)
                {
                    closeMenu();
                }
                else
                {
                    openMenu();
                }
                break;
            default:
                Toast.makeText(this,v.getTag().toString(),Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //关闭菜单
    private void closeMenu() {
        for(int i=1;i<res.length;i++)
        {
            ObjectAnimator a1 = ObjectAnimator.ofFloat(list.get(i), "translationX",  100 * i,0);
            ObjectAnimator a2 = ObjectAnimator.ofFloat(list.get(i), "translationY",  100 * i,0);
            AnimatorSet set = new AnimatorSet();
            set.setDuration(500);
            set.setInterpolator(new BounceInterpolator());
            set.playTogether(a1,a2);
//            a1.setInterpolator(new BounceInterpolator());
//            a2.setInterpolator(new BounceInterpolator());
//            a1.start();
//            a2.start();
            set.start();
        }
        isOpen = false;
    }

    //打开菜单
    private void openMenu() {
        for(int i=1;i<res.length;i++)
        {
            ObjectAnimator a1 = ObjectAnimator.ofFloat(list.get(i), "translationX", 0, 100 * i).setDuration(500);
            ObjectAnimator a2 = ObjectAnimator.ofFloat(list.get(i), "translationY", 0, 100 * i).setDuration(500);
            a1.setInterpolator(new BounceInterpolator());
            a2.setInterpolator(new BounceInterpolator());
            a1.start();
            a2.start();
        }
        isOpen = true;
    }
}
