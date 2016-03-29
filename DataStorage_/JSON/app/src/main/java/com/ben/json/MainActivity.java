package com.ben.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jsonReaderClick(View view) {
        ArrayList<Person> list = jsonReader();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private ArrayList<Person> jsonReader() {
//        String json = "";
        Reader r = new InputStreamReader(getResources().openRawResource(R.raw.person));
        JsonReader jr = new JsonReader(r);
        ArrayList<Person> list = new ArrayList<>();


        try {
            jr.beginObject();
            if (jr.hasNext()) {
                if ("user".equals(jr.nextName())) {
                    jr.beginArray();//开始解析数组
                    while (jr.hasNext()) {
                        Person p = new Person();
                        jr.beginObject();//开始解析对象
                        while (jr.hasNext()) {
                            String name = jr.nextName();
                            if ("firstName".equals(name)) {
                                p.firstName = jr.nextString();
                            } else if ("lastName".equals(name)) {
                                p.lastName = jr.nextString();
                            } else if ("email".equals(name)) {
                                p.email = jr.nextString();
                            }

                        }
                        jr.endObject();//结束对象的解析
                        list.add(p);
                    }
                    jr.endArray();
                }
            }
            jr.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /*
    生成JSON数据
     */
    public void createJSONClick(View view) {
        ArrayList<Person> list = new ArrayList<>();
        Person p1 = new Person();
        p1.firstName = "meng";
        p1.lastName = "ben";
        p1.email = "877@qq.com";
        Person p2 = new Person();
        p2.firstName = "long";
        p2.lastName = "wu";
        p2.email = "528@qq.com";
        list.add(p1);
        list.add(p2);

        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        try {
            for (int i = 0; i < list.size(); i++) {
                Person p = list.get(i);
                JSONObject obj = new JSONObject();


                obj.put("firstName", p.firstName);
                obj.put("lastName", p.lastName);
                obj.put("email", p.email);
                array.put(obj);
            }
            json.put("person", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(json.toString());
    }

    //gson解析JSON
    /*
    解析的时候json文件中user不能要
     */
    public void gsonParseJSONCLick(View view)
    {
        Gson gson = new Gson();
        Reader r = new InputStreamReader(getResources().openRawResource(R.raw.person));
        Type type = new TypeToken<ArrayList<Person>>(){}.getType();
        ArrayList<Person> list = gson.fromJson(r,type);
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }
    }
//    class MyTypeToken extends TypeToken<ArrayList<Person>>{}
    //gson生成JSON
    public void gsonToJSONClick(View view)
    {
        ArrayList<Person> list = new ArrayList<>();
        Person p1 = new Person();
        p1.firstName = "meng";
        p1.lastName = "ben";
        p1.email = "877@qq.com";
        Person p2 = new Person();
        p2.firstName = "long";
        p2.lastName = "wu";
        p2.email = "528@qq.com";
        list.add(p1);
        list.add(p2);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Person>>(){}.getType();
        String json = gson.toJson(list,type);
        System.out.println(json);
    }
}
