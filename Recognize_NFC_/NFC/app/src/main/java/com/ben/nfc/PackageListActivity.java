package com.ben.nfc;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PackageListActivity extends AppCompatActivity implements OnItemClickListener {

    private ListView listView;
    private List<AppInfo> list = new ArrayList<>();
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        PackageManager pm = getPackageManager();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(PackageManager.GET_ACTIVITIES);
        for(PackageInfo p:packageInfos)
        {
            AppInfo appInfo = new AppInfo();
            appInfo.appName = p.applicationInfo.loadLabel(pm).toString();
            appInfo.packageName = p.packageName;
            appInfo.versionName = p.versionName;
            appInfo.versionCode = p.versionCode;
            appInfo.appicon = p.applicationInfo.loadIcon(getPackageManager());
            list.add(appInfo);
        }
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AppInfo appInfo = list.get(position);
        Intent data = new Intent();
        data.putExtra("packageName",appInfo.packageName);
        setResult(RESULT_OK, data);
        finish();
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh = null;
            if(convertView == null)
            {
                convertView = getLayoutInflater().inflate(R.layout.list_item_layout,null);
                vh = new ViewHolder();
                vh.imageView_icon = (ImageView) convertView.findViewById(R.id.imageView_icon);
                vh.textView_intro = (TextView) convertView.findViewById(R.id.textView_intro);
                convertView.setTag(vh);
            }
            else
            {
                vh = (ViewHolder) convertView.getTag();
                AppInfo appInfo = list.get(position);
                vh.imageView_icon.setImageDrawable(appInfo.appicon);
                vh.textView_intro.setText(appInfo.appName);
            }
            return convertView;
        }

        class ViewHolder{
            ImageView imageView_icon;
            TextView textView_intro;
        }
    }
}
