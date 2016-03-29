package com.ben.alertdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

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

    /*
    1、提示对话框
     */
    public void dialogClick1(View v)
    {
        //创建一个提示对话框的构造者
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("请问你有女票吗");
        builder.setIcon(R.mipmap.ic_launcher);
        //正面的按钮
        builder.setPositiveButton("有", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你可以走了", Toast.LENGTH_SHORT).show();
            }
        });
        //设置中立的按钮
        builder.setNeutralButton("呵呵", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "First blood！", Toast.LENGTH_SHORT).show();
            }
        });
        //反面的按钮
        builder.setNegativeButton("没有", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "欢迎来到单身俱乐部", Toast.LENGTH_SHORT).show();
            }
        });

//        AlertDialog dialog = builder.create();
//        dialog.show();
        builder.show();
    }

    /*
    2、列表对话框
     */
    public void dialogClick2(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择一个平台：");
        final String [] items = {"Android","IOS","Window Phone"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,items[which],Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    /*
     3、多选列表对话框
    */
    public void dialogClick3(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择一个平台：");
        final String [] items = {"Android","IOS","Window Phone"};
        final ArrayList<String> list = new ArrayList<String>();
        builder.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                {
                    list.add(items[which]);
                }
                else
                {
                    list.remove(items[which]);
                }
            }
        });

        //正面的按钮
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置反面的按钮
        builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.clear();
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /*
    4、多项单选列表
     */
    String result = "Android";
    public void dialogClick4(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择一个平台：");

        final String [] items = {"Android","IOS","Window Phone"};
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"你选中了"+items[which],Toast.LENGTH_SHORT).show();
                    }
        });

        //正面的按钮
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置反面的按钮
        builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                result  = items[which];
                dialog.dismiss();
            }
        });
        builder.show();
    }
    /*
    5、自定义对话框
     */
    public void dialogClick5(View v)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("登陆对话框");

//        builder.setView(R.layout.login_layout); API21   5.0支持
        final View view = getLayoutInflater().inflate(R.layout.login_layout,null);
        builder.setView(view);



        //正面的按钮
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText_username = (EditText)view.findViewById(R.id.editText_username);
                EditText editText_password = (EditText)view.findViewById(R.id.editText_password);

                String username = editText_username.getText().toString();
                String password = editText_password.getText().toString();

                Toast.makeText(MainActivity.this,username+"--"+password, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置反面的按钮
        builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();
            }
        });
        builder.show();
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
