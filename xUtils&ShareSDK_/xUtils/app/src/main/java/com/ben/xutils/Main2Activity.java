package com.ben.xutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/*


HttpUtils--------------------------
 */
public class Main2Activity extends AppCompatActivity {

    @ViewInject(R.id.editText_username)
     EditText editText_username;
    @ViewInject(R.id.editText_password)
     EditText editText_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ViewUtils.inject(this);

    }

    @OnClick(R.id.button_login)
    public void login(View view)
    {
        String username = editText_username.getText().toString();
        String password = editText_password.getText().toString();

        HttpUtils httpUtils =  new HttpUtils();
        String url = "http://10.101.242.34:8080/xUtilsServlet/LoginServlet";//没写
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("username",username);
        params.addQueryStringParameter("password",password);
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                if("1".equals(result))
                {
                    Toast.makeText(Main2Activity.this,"登陆成功",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(Main2Activity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(Main2Activity.this,s,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
