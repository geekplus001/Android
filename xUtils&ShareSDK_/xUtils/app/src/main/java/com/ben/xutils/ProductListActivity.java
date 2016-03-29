package com.ben.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import android.view.View;

public class ProductListActivity extends AppCompatActivity {

    private ListView productlistview;
    private  ProductListAdapter pla;
    private List<product> products = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //初始化Bmob
        Bmob.initialize(this,"22e7841ebd5350c218dc2185347182a1");
        productlistview = (ListView) findViewById(R.id.product_list_view);
        pla = new ProductListAdapter(ProductListActivity.this,products);
        loadData();
    }

    private void loadData() {
        final View loadView = getLayoutInflater().inflate(R.layout.load_data,null);
        productlistview.addHeaderView(loadView);
        productlistview.setAdapter(pla);
        BmobQuery<product> query = new BmobQuery<>();
        query.findObjects(ProductListActivity.this, new FindListener<product>() {
            @Override
            public void onSuccess(List<product> list) {
                System.out.println(list.size());
                products = list;
                productlistview.removeHeaderView(loadView);
                pla.setProducts(products);
                pla.notifyDataSetChanged();
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
