package com.ben.loaders;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Person>>{

    private MyAdapter myAdapter;
    private DataBaseAdapter dataBaseAdapter;
    private DataAsyncTaskLoader loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView listView = (ListView) findViewById(R.id.listView2);
        dataBaseAdapter = new DataBaseAdapter(this);
        myAdapter = new MyAdapter(this,dataBaseAdapter.findAll());
        listView.setAdapter(myAdapter);

        getLoaderManager().initLoader(0,null,this);

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

    public void addClick2(View view)
    {
        dataBaseAdapter.save(new Person("ben",21));
        loader.onContentChanged();
    }



    //自定义Loader
    private static class DataAsyncTaskLoader extends AsyncTaskLoader<ArrayList<Person>>
    {

        private DataBaseAdapter dataBaseAdapter;
        private ArrayList<Person> data;
        public DataAsyncTaskLoader(Context context,DataBaseAdapter dataBaseAdapter) {
            super(context);
            this.dataBaseAdapter = dataBaseAdapter;
        }

        //该方法在后台线程中执行，加载数据
        @Override
        public ArrayList<Person> loadInBackground() {
            System.out.println("loadInBackground");
            return dataBaseAdapter.findAll();
        }

        //以下为重写方法
        //用于发送结果
        @Override
        public void deliverResult(ArrayList<Person> data) {
            if(isReset())
            {
                return;
            }
            if(isStarted())
            {

                super.deliverResult(data);
            }
        }

        @Override
        protected void onStartLoading() {
            if(data!=null)
            {
                deliverResult(data);
            }
            if(takeContentChanged())
            {
                forceLoad();//强制加载数据loadInBackground
            }
            super.onStartLoading();
        }
    }

    private static class MyAdapter extends BaseAdapter{

        private ArrayList<Person> persons;
        private Context context;

        private void setPersons(ArrayList<Person> persons)
        {
            this.persons = persons;
        }


        public MyAdapter(Context context,ArrayList<Person> persons)
        {
            this.context = context;
            this.persons = persons;
        }


        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int position) {
            return persons.get(position);
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
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
                vh = new ViewHolder();
                vh.tv_id = (TextView) convertView.findViewById(R.id.textView_id);
                vh.tv_name = (TextView) convertView.findViewById(R.id.textView2_name);
                vh.tv_age = (TextView) convertView.findViewById(R.id.textView3_age);
                convertView.setTag(vh);
            }
            else
            {
                vh = (ViewHolder) convertView.getTag();
            }
            Person p = persons.get(position);
            vh.tv_id.setText(String.valueOf(p.getId()));
            vh.tv_name.setText(p.getName());
            vh.tv_age.setText(String.valueOf(p.getAge()));

            return convertView;
        }

        private static class ViewHolder{
            TextView tv_id;
            TextView tv_name;
            TextView tv_age;
        }


    }

    @Override
    public Loader<ArrayList<Person>> onCreateLoader(int id, Bundle args) {
        loader = new DataAsyncTaskLoader(this,dataBaseAdapter);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Person>> loader, ArrayList<Person> data) {
        myAdapter.setPersons(data);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Person>> loader) {
        myAdapter.setPersons(null);
    }

}
