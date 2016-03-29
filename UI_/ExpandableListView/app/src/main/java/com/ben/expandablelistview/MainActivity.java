package com.ben.expandablelistview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setAdapter(new MyExpandableAdapter());

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MainActivity.this,childs[groupPosition][childPosition],Toast.LENGTH_SHORT).show();
                return true;
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

    private String[] groups = {"好友","同学"};
    private String[][] childs = {{"蛋蛋","空空","莉莉"},{"藤篮","莉亚","结衣"}};
    class MyExpandableAdapter extends BaseExpandableListAdapter{



        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childs[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childs[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {

            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if(convertView == null)
            {
                convertView = getLayoutInflater().inflate(R.layout.group_layout,null);

                vh = new ViewHolder();
                vh.imageView_icon = (ImageView) convertView.findViewById(R.id.icon);
                vh.textView_title = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }

            vh.textView_title.setText(groups[groupPosition]);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if(convertView == null)
            {
                convertView = getLayoutInflater().inflate(R.layout.child_layout,null);

                vh = new ViewHolder();
                vh.imageView_icon = (ImageView) convertView.findViewById(R.id.icon);
                vh.textView_title = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(vh);
            }else{
                vh = (ViewHolder) convertView.getTag();
            }

            vh.textView_title.setText(childs[groupPosition][childPosition]);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
         class ViewHolder{
            ImageView imageView_icon;
             TextView textView_title;
        }
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
}
