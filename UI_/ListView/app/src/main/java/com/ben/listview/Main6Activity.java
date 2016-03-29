package com.ben.listview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

import java.util.logging.LogRecord;

public class Main6Activity extends AppCompatActivity implements AbsListView.OnScrollListener
{

    private ListView listView;
    private Vector<News> news = new Vector<>();
    private MyAdapter myAdapter;
    private static final int DATA_UPDATE = 0x1;//数据更新完成后的标记

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        listView = (ListView) findViewById(R.id.listView5);
        listView.setOnScrollListener(this);
        View footView =  getLayoutInflater().inflate(R.layout.loading,null);
        listView.addFooterView(footView);
        initData();
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }
    private int index = 1;
    //初始化数据
    private void initData()
    {
        for(int i=0;i<10;i++)
        {
            News n = new News();
            n.title = "title--"+index;
            n.content = "content--"+index;
            index++;
            news.add(n);
        }
    }

    private int visibleLastIndex;//是否到最后一条
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(myAdapter.getCount()==visibleLastIndex && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE)
        {
            new LoadDataThread().start();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        visibleLastIndex = firstVisibleItem+visibleItemCount-1;
    }

    //线程之间通讯的机制
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case DATA_UPDATE:
                    myAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    class LoadDataThread extends Thread{
        @Override
        public void run() {
            initData();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            myAdapter.notifyDataSetChanged();
            //通过Handler给主线程发送一个消息标记
            handler.sendEmptyMessage(DATA_UPDATE);
        }

    }

     class MyAdapter extends BaseAdapter{

//         private Context context;
//         public MyAdapter(Context context)
//         {
//             this.context = context;
//         }

        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return news.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if(convertView==null)
            {
//                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = getLayoutInflater().inflate(R.layout.list_item_6,null);
//                inflater.inflate(R.layout.list_item_6,null);
                vh = new ViewHolder();
                vh.tv_title = (TextView) convertView.findViewById(R.id.textView_title);
                vh.tv_content = (TextView) convertView.findViewById(R.id.textView_content);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }
            News n = news.get(position);
            vh.tv_title.setText(n.title);
            vh.tv_content.setText(n.content);
            return convertView;
        }


          class ViewHolder{
             TextView tv_title;
             TextView tv_content;
         }
    }
}
