package com.ben.benbaidumap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView = null;
    private BaiduMap baiduMap = null;
    private PoiSearch poiSearch = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);

        baiduMap = mMapView.getMap();//获得地图
//        baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//设置为卫星图模式
//        baiduMap.setTrafficEnabled(true);//实时交通图

        //添加一个标注覆盖物
        LatLng point = new LatLng(39.963175,116.400244);//纬度和经度
        BitmapDescriptor bitmapDescriptor =
                BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);
        //创建一个图层选项
        OverlayOptions overlayOptions = new MarkerOptions().
                position(point).icon(bitmapDescriptor);
        //把图层选项添加到地图
        baiduMap.addOverlay(overlayOptions);

        //
        poiSearch = PoiSearch.newInstance();
        //POI检索结果的监听事件
        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener(){
            public void onGetPoiResult(PoiResult result){
                //获取POI检索结果
                List<PoiInfo> poiInfos = result.getAllPoi();
                for(PoiInfo p:poiInfos)
                {
                    System.out.println(p.address+"----"+p.phoneNum);
                    BitmapDescriptor bd = BitmapDescriptorFactory.fromResource(R.mipmap.o);
                    OverlayOptions overlayOptions1 = new MarkerOptions().position(p.location).icon(bd);
                    baiduMap.addOverlay(overlayOptions1);
                }

            }
            public void onGetPoiDetailResult(PoiDetailResult result){
                //获取Place详情页检索结果
            }
        };

        //设置POI检索监听者；
        poiSearch.setOnGetPoiSearchResultListener(poiListener);

    }


    public void poiClick(View view)
    {
        //发起检索请求；异步过程
        poiSearch.searchInCity((new PoiCitySearchOption())
                .city("北京")
                .keyword("美食")
                .pageNum(10));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();

//        poiSearch.destroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
