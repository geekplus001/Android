package com.ben.toast;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定布局文件
        setContentView(R.layout.activity_main);
    }
    /*
    按钮的单击事件方法：
    写法：
     */
    public void viewText(View v)
    {
        //getApplicationContext()应用程序上下文，作用域是整个程序
        Toast.makeText(getApplicationContext(),"今天天气不错",Toast.LENGTH_LONG).show();
    }

    /*
    显示图片的Toast
     */
    public void viewImage(View v)
    {
        Toast t = new Toast(this);
        //用于显示图片的组件
        ImageView imageView = new ImageView(this);
        //为图片组件设置图片
        imageView.setImageResource(R.drawable.a);

        t.setView(imageView);
        t.setDuration(Toast.LENGTH_LONG);
        //设置显示位置
        t.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
        t.show();
    }
    /*
    显示图文的Toast
     */
    public void viewImageText(View v)
    {
        Toast t = new Toast(this);
        TextView textView = new TextView(this);
        textView.setText("cute boy");
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.a);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.addView(imageView);
        layout.addView(textView);

        t.setView(layout);

        t.setDuration(Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);

        t.show();
    }
}
