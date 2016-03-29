package com.ben.gridview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {


    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gridView = (GridView) findViewById(R.id.gridView2);
        gridView.setAdapter(new MyAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("parent="+parent);//GridView
                System.out.println("view="+view);//，每一个选项的布局
                System.out.println("position=" + position);//位置
                System.out.println("id=" + id);//id
                TextView tv = (TextView) view.findViewById(R.id.textView);
//                Toast.makeText(Main2Activity.this,"gridview",Toast.LENGTH_SHORT).show();
                Toast.makeText(Main2Activity.this,tv.getText(),Toast.LENGTH_SHORT).show();
            }
        });
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

    static class MyAdapter extends BaseAdapter
    {

        private String[] names = {"转账","查询","金融","基金","国债","信用卡","商城","充值"};
        private Context context;
        private int[] images = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
        public MyAdapter(Context context)
        {
            this.context = context;
        }
        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.item, null);
            ImageView iv = (ImageView) view.findViewById(R.id.imageView);
            TextView tv = (TextView) view.findViewById(R.id.textView);
            iv.setImageResource(images[position]);
            tv.setText(names[position]);
            return view;
        }
    }
}
