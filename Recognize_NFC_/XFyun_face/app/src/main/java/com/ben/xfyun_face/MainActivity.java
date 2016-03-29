package com.ben.xfyun_face;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iflytek.cloud.FaceRequest;
import com.iflytek.cloud.RequestListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_CAPTURE_CAMEIA = 0x1;
    private static final int REQUEST_CODE_CAPTURE_CAMEIA_LOGIN = 0x2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        //初始化
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=56f7a051");
    }

    //人脸注册
    public void regClick(View view)
    {
//        String state = Environment.getExternalStorageState();
//        if(state.equals(Environment.MEDIA_MOUNTED))
//        {
            Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivityForResult(getImageByCamera, REQUEST_CODE_CAPTURE_CAMEIA);
//        }
//        else
//        {
//            Toast.makeText(getApplicationContext(),"请确认已经插入SD卡",Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode==REQUEST_CODE_CAPTURE_CAMEIA)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);

            // 使用FaceRequest(Context context)构造一个FaceRequest对象
            FaceRequest face = new FaceRequest(this);
            // 设置业务类型为注册
            face.setParameter(SpeechConstant.WFR_SST, "reg");
            // 设置auth_id
            face.setParameter(SpeechConstant.AUTH_ID, "ben001");

            //把bitmap对象转换成字节数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            byte[] imgData = out.toByteArray();

            // 调用sendRequest(byte[] img, RequestListener listener)方法发送注册请求，img为图片的二进制数据，listener为处理注册结果的回调对象
            face.sendRequest(imgData, mRequestListener);
        }
        else if(resultCode== Activity.RESULT_OK && requestCode==REQUEST_CODE_CAPTURE_CAMEIA_LOGIN)
        {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            FaceRequest face = new FaceRequest(this);
            // 设置业务类型为验证
            face.setParameter(SpeechConstant.WFR_SST, "verify");
            // 设置auth_id
            face.setParameter(SpeechConstant.AUTH_ID, "ben001");
            // 调用sendRequest(byte[] img, RequestListener listener)方法发送注册请求，img为图片的二进制数据，listener为处理注册结果的回调对象
            //把bitmap对象转换成字节数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            byte[] imgData = out.toByteArray();
            face.sendRequest(imgData, mRequestListener);
//            // 设置业务类型为验证
//            face.setParameter(SpeechConstant.WFR_SST, "detect");
//            // 调用sendRequest(byte[] img, RequestListener listener)方法发送注册请求，img为图片的二进制数据，listener为处理注册结果的回调对象
//            face.sendRequest(imgData, mRequestListener);
        }
    }

    RequestListener mRequestListener = new RequestListener() {
        // 获得结果时返回，JSON格式。
        public void onBufferReceived(byte[] buffer) {
            String json = new String(buffer);
            Toast.makeText(MainActivity.this,json,Toast.LENGTH_LONG).show();
            System.out.println(json);
        }

        // 流程结束时返回，error不为空则表示发生错误。
        public void onCompleted(SpeechError error) {
            if(error==null)
            {
                Toast.makeText(MainActivity.this,"成功~~",Toast.LENGTH_SHORT).show();
            }
        }

        // 保留接口，扩展用。
        public void onEvent(int eventType, Bundle params) {
        }
    };

    public void loginClick(View view)
    {
        Intent getImageByCamera = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(getImageByCamera, REQUEST_CODE_CAPTURE_CAMEIA_LOGIN);
    }
}
