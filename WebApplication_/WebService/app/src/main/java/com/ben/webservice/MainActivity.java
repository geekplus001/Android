package com.ben.webservice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    //命名空间
    String nameSpace = "http://WebXml.com.cn/";
    //调用的方法名称
    String methodName = "getMobileCodeInfo";
    //EndPoint
    String endPoint = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx?WSDL";
    //SOAP Action
    String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";

    public void webServiceClick(View view)
    {
       new Thread(new Runnable() {
           @Override
           public void run() {
               //指定WebService的命名空间和调用方法名
               SoapObject rpc = new SoapObject(nameSpace,methodName);
               //设置需要调用WebService接口需要传入的两个参数mobileCode、userId
               rpc.addProperty("mobileCode","13592127437");
               rpc.addProperty("userId","");

               //生成调用WebService方法的Soap请求信息，并制定Soap版本
               SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
               envelope.bodyOut = rpc;
               envelope.dotNet = true;
               HttpTransportSE transportSE = new HttpTransportSE(endPoint);
               try {
                   transportSE.call(soapAction,envelope);//调用WebService

               } catch (IOException e) {
                   e.printStackTrace();
               } catch (XmlPullParserException e) {
                   e.printStackTrace();
               }
               //获取返回的数据
               SoapObject object = (SoapObject) envelope.bodyIn;
               //获取返回的结果
               String result = object.getProperty(0).toString();
               System.out.println(result);
           }
       }).start();
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
