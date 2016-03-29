package com.ben.pulltorefresh;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView lv;
    private ArrayList<Music> musics = new ArrayList<>();
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
//        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            @Override
//            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
//
//            }
//        });
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new LoadDataAsyncTask(MainActivity.this).execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new LoadDataAsyncTask(MainActivity.this).execute();
            }
        });
        lv.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新时显示的文本
        ILoadingLayout startLayout = lv.getLoadingLayoutProxy(true, false);
        startLayout.setPullLabel("正在下拉刷新...");
        startLayout.setRefreshingLabel("正在玩命加载中...");
        startLayout.setReleaseLabel("放开刷新...");

        ILoadingLayout endLayout = lv.getLoadingLayoutProxy(false, true);
        endLayout.setPullLabel("正在上拉刷新...");
        endLayout.setRefreshingLabel("正在玩命加载中...");
        endLayout.setReleaseLabel("放开...");

        loadData();
        dataAdapter = new DataAdapter(this,musics);
        lv.setAdapter(dataAdapter);
        System.out.println("90000000000000000000000000000000000");
        Log.i("ooo","99999999999999999999999999999999999999999999999999");
    }

    private int count = 1;
    private void loadData()
    {
        for(int i=0;i<10;i++)
        {
            musics.add(new Music("歌曲："+count,"歌手："+count));
            count++;
        }
    }

    static  class LoadDataAsyncTask extends AsyncTask<Void,Void,String>{

        private MainActivity activity;
        public LoadDataAsyncTask(MainActivity activity)
        {
            this.activity = activity;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            activity.loadData();
            return "success";
        }

        //判断完成
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if("success".equals(s))
            {
                activity.dataAdapter.notifyDataSetChanged();//通知数据集改变
                activity.lv.onRefreshComplete();//表示刷新完成
            }
        }
    }

    static class DataAdapter extends BaseAdapter{

        private Context context;
        private ArrayList<Music> musics;
        public DataAdapter(Context context,ArrayList<Music>  musics)
        {
            this.context = context;
            this.musics = musics;
        }

        @Override
        public int getCount() {
            return musics.size();
        }

        @Override
        public Object getItem(int position) {
            return musics.get(position);
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
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
                vh = new ViewHolder();
                vh.tv_tile = (TextView) convertView.findViewById(R.id.textView_title);
                vh.tv_singer = (TextView) convertView.findViewById(R.id.textView_singer);
                convertView.setTag(vh);
            }
            vh = (ViewHolder) convertView.getTag();
            Music m = musics.get(position);
            vh.tv_tile.setText(m.getTitle());
            vh.tv_singer.setText(m.getSinger());
            return convertView;
        }

        static class ViewHolder{
            TextView tv_tile;
            TextView tv_singer;
        }
    }
}
