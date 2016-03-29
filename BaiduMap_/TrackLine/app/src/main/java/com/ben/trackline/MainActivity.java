package com.ben.trackline;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Geocoder;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public static final int PLAYBACK_OVER = 1;
    private MapView mapView;
    private BaiduMap baiduMap;
    private LocationClient mLocationClient = null;
    private MyLocationListener myLocationListener;
    private double currentLat,currentLng;//当前经纬度
    private String currentAddr;//当前地址address
    private DataBaseAdapter dbAdapter;
    private GeoCoder geocoder;
    private int currentTrackLineID;//当前跟踪线路的ID


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.bmapView);


        initBitMap();
        dbAdapter = new DataBaseAdapter(this);
    }

    //初始化百度地图
    private void initBitMap() {
        baiduMap = mapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);//注册监听函数



        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
        mLocationClient.start();

        //用于转换地理编码
        geocoder = GeoCoder.newInstance();
        geocoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if(reverseGeoCodeResult==null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR)
                {
                    //没有检索结果
                }
                else
                {
                    //获取地理编码结果
                    currentAddr = reverseGeoCodeResult.getAddress();
                    //更新线路的终点
                    dbAdapter.updateEndLoc(currentAddr,currentTrackLineID);
                }
            }
        });
    }
    boolean flag = true;

     class MyLocationListener implements BDLocationListener {

        //接受位置信息的回调方法
        @Override
        public void onReceiveLocation(BDLocation location) {

            if (location!=null && flag)
            {
                flag = false;
                currentLat = location.getLatitude();
                currentLng = location.getLongitude();
                currentAddr = location.getAddrStr();
                MyLocationData.Builder buidler = new MyLocationData.Builder();
                buidler.latitude(location.getLatitude());
                buidler.longitude(location.getLongitude());
                buidler.accuracy(location.getRadius());
                buidler.direction(location.getDirection());
                buidler.speed(location.getSpeed());
                MyLocationData locationData = buidler.build();

                //把我的位置信息设置到地图上
                baiduMap.setMyLocationData(locationData);
                LatLng latLng  = new LatLng(currentLat,currentLng);
                baiduMap.setMyLocationConfigeration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING,
                        true,null));
                //设置我的位置为地图中心
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(latLng,16));
            }
//            /

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*
    功能菜单项
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.mylocation:
                mylocation();
                break;
            case R.id.start_track:
                startTrack();
                break;
            case R.id.end_track:
                endTrack();
                break;
            case R.id.track_back:
                trackBack();
                break;
            default:
                break;
        }
        return true;
    }

    /*
    我的位置
     */
    private void mylocation() {
        Toast.makeText(MainActivity.this,"正在定位中",Toast.LENGTH_SHORT).show();
        flag = true;
        baiduMap.clear();//清除地图上自定义的图层
        baiduMap.setMyLocationEnabled(true);
        mLocationClient.start();
    }
    /*
      开始跟踪功能
    */
    private void startTrack() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("线路跟踪");
        builder.setCancelable(true);
        final View view = getLayoutInflater().inflate(R.layout.add_track_line_dialog,null);
        builder.setView(view);
        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText1 = (EditText) view.findViewById(R.id.editText1_track_name);
                String trackName = editText1.getText().toString();
                createTrack(trackName);//创建跟踪路线的方法
                Toast.makeText(MainActivity.this, "跟踪开始", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    //创建线路跟踪
    private void createTrack(String trackName)
    {
        Track track = new Track();
        track.setTrack_name(trackName);
        track.setCreate_date(DataUtils.toDate(new Date()));
        track.setStart_loc(currentAddr);
        currentTrackLineID = dbAdapter.addTrack(track);
        dbAdapter.addTrackDetail(currentTrackLineID,currentLat,currentLng);
        baiduMap.clear();
        addOverLay();
        list.add(new LatLng(currentLat, currentLng));
        isTracking = true;
        System.out.println(list);
        new Thread(new TrackThread()).start();
    }
    private boolean isTracking = false;//线程模拟标记
    /*
    结束跟踪
     */
    private void endTrack()
    {
        isTracking = false;//结束线程
        Toast.makeText(MainActivity.this,"跟踪结束",Toast.LENGTH_SHORT).show();
        //转换地理编码(经纬度与地址的转换)
        //最后一个经纬度转换成地址
        geocoder.reverseGeoCode(
                new ReverseGeoCodeOption().location(
                        new LatLng(currentLat, currentLng)));
    }
    /*
    回放
     */
    AlertDialog dialog = null;
    private void trackBack()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("跟踪路线列表");
        View view = getLayoutInflater().inflate(R.layout.track_playback_dialog,null);
        ListView playbackListView = (ListView) view.findViewById(R.id.listView_play_back);

        ArrayList<HashMap<String,String>> data = new ArrayList<HashMap<String, String>>();
        ArrayList<Track> tracks = dbAdapter.getTracks();
        HashMap<String,String> map = null;
        Track t = null;
        int s = tracks.size();
        for(int i=0;i<s;i++)
        {
            map = new HashMap<String,String>();
            t = tracks.get(i);
            map.put("id",String.valueOf(t.getId()));
            map.put("trackName_createDate",t.getTrack_name()+"--"+t.getCreate_date());
            map.put("startEndLoc","从["+t.getStart_loc()+"]到["+t.getEnd_loc()+"]");
            data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,data,R.layout.playback_item,new String[]{
                "id","trackName_createDate","startEndLoc"
        },new int[]{R.id.textView,R.id.textView2,R.id.textView3});
        playbackListView.setAdapter(adapter);
        playbackListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView1 = (TextView) view.findViewById(R.id.textView);
                int _id = Integer.parseInt(textView1.getText().toString());
                baiduMap.clear();
                new Thread(new TrackPlayBackThread(_id)).start();
                dialog.dismiss();
            }
        });
        builder.setView(view);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    //用于存储两个相邻的经纬度点，再画线
    private ArrayList<LatLng> list = new ArrayList<>();
    /*
    添加一个标注覆盖物在当前位置
     */
    private void addOverLay()
    {
        //goujianMaker图标
        baiduMap.setMyLocationEnabled(false);//关闭定位器
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.o);

        LatLng latLng = new LatLng(currentLat,currentLng);
        OverlayOptions option = new MarkerOptions().position(latLng).icon(bitmapDescriptor);
        //在地图上添加Marker并显示
        baiduMap.addOverlay(option);
        //把当前添加的位置作为地图的中心
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
    }
    private void drawLine()
    {
        //连线
        OverlayOptions lineOptions = new PolylineOptions().points(list).color(0xFFFF0000);
        baiduMap.addOverlay(lineOptions);
        list.remove(0);//两点一线
    }

    //模拟位置
    private void getLocation()
    {
        currentLat = currentLat + Math.random()/1000;
        currentLng = currentLng + Math.random()/1000;
    }



    /*
    模拟跟踪线程
     */
    class TrackThread implements Runnable{

        @Override
        public void run() {
            while(isTracking)
            {
                getLocation();
                dbAdapter.addTrackDetail(currentTrackLineID, currentLat, currentLng);
                addOverLay();
                list.add(new LatLng(currentLat, currentLng));
                drawLine();
                System.out.println("drawline---------");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    回放线程
     */
    class TrackPlayBackThread implements Runnable{

        private int id;
        public TrackPlayBackThread(int id){
            this.id = id;
        }
        @Override
        public void run() {
            ArrayList<TrackDetail> trackDetails = dbAdapter.getTrackDetails(id);
            TrackDetail td = null;
            list.clear();
            currentLat = trackDetails.get(0).getLat();
            currentLng = trackDetails.get(0).getLng();
            list.add(new LatLng(currentLat, currentLng));
            addOverLay();
            int s = trackDetails.size();
            for(int i=0;i<s;i++)
            {
                td = trackDetails.get(i);
                currentLat = td.getLat();
                currentLng = td.getLng();
                list.add(new LatLng(currentLat,currentLng));
                addOverLay();
                drawLine();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handler.sendEmptyMessage(PLAYBACK_OVER);
        }
    }
    private Handler handler = new Handler(){
      public void handleMessage(Message msg)
      {
          switch (msg.what)
          {
              case  PLAYBACK_OVER:
                  Toast.makeText(MainActivity.this,"回放结束",Toast.LENGTH_SHORT).show();
                  break;
              default:
                  break;
          }
      }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();

//        poiSearch.destroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

}
