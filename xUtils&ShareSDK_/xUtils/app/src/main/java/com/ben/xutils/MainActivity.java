package com.ben.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;
/*


DBUtils-----------------------------
ViewUtils--------------------------//不建议使用

 */

public class MainActivity extends AppCompatActivity {

    DbUtils dbUtils;

    @ViewInject(R.id.textView_data)
    private TextView textView_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       ViewUtils.inject(this);//注入view和事件

        dbUtils = DbUtils.create(this,"mydb");
    }

    @OnClick(R.id.button_show)
    public void show(View view)
    {
        textView_data.setText("view Utils 的使用");
    }


    public void saveClick(View view)
    {

        User user = new User("binge","binge@qq.com");
        try {
            dbUtils.save(user);
            Toast.makeText(this,"save success",Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            e.printStackTrace();
        }

    }

    public void findAllClick(View view)
    {
        try {
            List<User> users = dbUtils.findAll(User.class);
            for(User u:users)
            {
                System.out.println(u.toString());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void selectAllClick(View view)
    {
        try {
            User user = dbUtils.findFirst(Selector.from(User.class).where("name","=","binge"));
            Toast.makeText(this,user.toString(),Toast.LENGTH_SHORT).show();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    public void updateClick(View view)
    {
        User user = new User();
        user.setId(1);
        user.setName("binbinbinbbin");
        user.setEmail("aaaa@qq.com");
        try {
            dbUtils.update(user,"name","email");
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public void deleteClick(View view)
    {
        try {
            dbUtils.delete(User.class, WhereBuilder.b("id","=","2"));
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

}
