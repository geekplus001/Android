package com.ben.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivityResult extends AppCompatActivity {

    private static final int REQUESTCODE_1 = 0x1;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_result);
        editText = (EditText) findViewById(R.id.editText2);

    }
    /*
    选择一个电话号码
     */
    public void selectClick(View v)
    {
        Intent intent = new Intent(this,PhoneNumberListActivity.class);
        //intent,请求编码
        startActivityForResult(intent, REQUESTCODE_1);
    }


    /*
    重写该方法处理返回结果

     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUESTCODE_1 && resultCode==RESULT_OK)
        {
            String number = data.getStringExtra("number");
            editText.setText(number);
        }
    }

    /*
        拨打电话
         */
    public void callClick(View v)
    {
        String number = editText.getText().toString();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+number));
        startActivity(intent);
    }

}
