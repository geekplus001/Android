package com.ben.listview;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/*
使用自定义适配器:
优化：
1、使ListView宽高固定，避免内容变化导致ListView重新渲染
2、使用ConvertView减少对象的创建
3、使用ViewHolder减少对象的查找
 */
public class Main5Activity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        listView = (ListView) findViewById(R.id.listView4);
        listView.setAdapter(new MyAdapter(this));

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

    static class MyAdapter extends BaseAdapter{

        private String [] titles = {"title-1","title-2","title-3","title-4","title-5",
                "title-6","title-7","title-8","title-9","title-10",
                "title-11","title-12","title-13","title-14","title-15"};
        private int[] icons = {android.R.drawable.ic_input_add,
                android.R.drawable.ic_input_delete,
                android.R.drawable.ic_dialog_email,
                android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_dialog_map,
                android.R.drawable.ic_input_add,
                android.R.drawable.ic_input_delete,
                android.R.drawable.ic_dialog_email,
                android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_dialog_map,
                android.R.drawable.ic_input_add,
                android.R.drawable.ic_input_delete,
                android.R.drawable.ic_dialog_email,
                android.R.drawable.ic_dialog_info,
                android.R.drawable.ic_dialog_map
        };
        private Context context;
        public MyAdapter(Context context)
        {
            this.context = context;
        }
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
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
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.list_item_5, null);

                vh = new ViewHolder();
                vh.iv_icon = (ImageView) convertView.findViewById(R.id.imageView_icon);
                vh.tv_title = (TextView) convertView.findViewById(R.id.textView_title);
                convertView.setTag(vh);
            }else {
                vh = (ViewHolder) convertView.getTag();
            }

//            TextView tv_title = (TextView) convertView.findViewById(R.id.textView_title);
//            ImageView iv_icon = (ImageView) convertView.findViewById(R.id.imageView_icon);

            vh.tv_title.setText(titles[position]);
            vh.iv_icon.setImageResource(icons[position]);

            return convertView;
        }
        //用于保存第一次查找的组件，避免下一次重新查找
        static class ViewHolder
        {
            ImageView iv_icon;
            TextView tv_title;
        }

    }

}
