package com.ben.xml;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //使用pull解析XML文件
    public void pullpaserClick(View view)
    {
        ArrayList<Person> list = parser();
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
    }

    private ArrayList<Person> parser() {
        ArrayList<Person> list = new ArrayList<>();
        Person p = null;

        //创建PULL解析爱
        XmlPullParser pull = Xml.newPullParser();
//        StringReader sr = new StringReader(xml);
        InputStream in = getResources().openRawResource(R.raw.person);
        try {
            //设置要解析的文件流
            pull.setInput(in,"UTF-8");
            //获取当前的事件类型
            int eventType = pull.getEventType();


            while(eventType!=XmlPullParser.END_DOCUMENT)
            {
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        String tag = pull.getName();
                        if("person".equals(tag))
                        {
                            p = new Person();
                            p.id = Integer.parseInt(pull.getAttributeValue(null,"personid"));
                        }else if("name".equals(tag))
                        {
                            p.name = pull.nextText();
                        }else if("age".equals(tag))
                        {
                            p.age = Integer.parseInt(pull.nextText());
                        }else if("sex".equals(tag))
                        {
                            p.sex = pull.nextText();
                        }else if("tel".equals(tag))
                        {
                            p.tel = pull.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if("person".equals(pull.getName()))
                        {
                            list.add(p);
                        }
                        break;
                }
                //获取下一个事件类型
                eventType = pull.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
