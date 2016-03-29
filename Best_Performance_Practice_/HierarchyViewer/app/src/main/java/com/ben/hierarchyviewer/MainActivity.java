package com.ben.hierarchyviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.imageView);
        iv.setOnClickListener(this);

        ListView lv = (ListView) findViewById(R.id.listView );

        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            HashMap<String,String> map = new HashMap<>();
            map.put("title","title-"+i);
            map.put("content","content-"+i);
            list.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.item2,new String[]{"title","content"},new int[]{R.id.textView3,R.id.textView4});
        lv.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.imageView)
        {
            ViewStub vs = (ViewStub) findViewById(R.id.stub_import);
            vs.setVisibility(View.VISIBLE);

            //方法二
//            View view = vs.inflate();
//            view.setVisibility(View.VISIBLE);
        }

    }
}
