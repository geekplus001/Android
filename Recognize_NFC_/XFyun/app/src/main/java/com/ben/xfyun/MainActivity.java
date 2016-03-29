package com.ben.xfyun;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {

    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();
    private TextView textView_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_content = (TextView) findViewById(R.id.textView_content);
        //初始化
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=56f7a051");
    }

    public void startSpeechClick(View view)
    {
        //1.创建SpeechRecognizer对象，第二个参数：本地识别时传InitListener
        SpeechRecognizer mIat= SpeechRecognizer.createRecognizer(this, null);
        //2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");//应用领域
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");//中文
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin");//普通话
        mIat.setParameter(SpeechConstant.ENGINE_TYPE,SpeechConstant.TYPE_CLOUD);//引擎
        //设置返回结果格式
        mIat.setParameter(SpeechConstant.RESULT_TYPE,"json");
        //设置不说话超时
        mIat.setParameter(SpeechConstant.VAD_BOS,"4000");
        //设置说话结束超时时间
        mIat.setParameter(SpeechConstant.VAD_EOS,"3000");
        //设置标点符号  0表示无标点 1表示有
        mIat.setParameter(SpeechConstant.ASR_PTT,"1");
        //可以音频保存路径（.pcm）
//        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS)+"ddd.pcm");
        //设置听写结果是否动态修正 为1则动态递增的返回结果   否则只在听写结束后返回最后结果
        //该参数只对在线听写有效
        mIat.setParameter(SpeechConstant.ASR_DWA,"0");


//        //1.创建RecognizerDialog对象
//        RecognizerDialog mDialog = new RecognizerDialog(this, mInitListener);
//        //2.设置accent、language等参数
//        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
//        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
//        //若要将UI控件用于语义理解， 必须添加以下参数设置， 设置之后onResult回调返回将是语义理解
//        //结果
//        // mDialog.setParameter("asr_sch", "1");
//        // mDialog.setParameter("nlp_version", "2.0");
//        //3.设置回调接口
//        mDialog.setListener(mRecognizerDialogListener);
//        //4.显示dialog，接收语音输入
//        mDialog.show();


        //3.开始听写
        mIat.startListening(mRecoListener);
    }

    //听写监听器
    private RecognizerListener mRecoListener = new RecognizerListener(){
        //听写结果回调接口(返回Json格式结果，用户可参见附录13.1)；
        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        //关于解析Json的代码可参见Demo中JsonParser类；
        //isLast等于true时会话结束。
        public void onResult(RecognizerResult results, boolean isLast) {
            Log.d("Result:", results.getResultString());
            Toast.makeText(MainActivity.this, results.getResultString(), Toast.LENGTH_SHORT).show();
            printResult(results);
        }
        //会话发生错误回调接口
        public void onError(SpeechError error) {

        }
        //开始录音
        public void onBeginOfSpeech() {

        }
        //volume音量值0~30，data音频数据
        public void onVolumeChanged(int volume, byte[] data){

        }
        //结束录音
        public void onEndOfSpeech() {

        }
        //扩展用接口
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

        }
    };

    //输出结果
    private void printResult(RecognizerResult results) {
        String text = JsonParser.parseIatResult(results.getResultString());

        String sn = null;
        //读取json结果中的sn字段
        try {
            JSONObject resultsJson  = new JSONObject(results.getResultString());
            sn = resultsJson.optString("sn");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mIatResults.put(sn,text);

        StringBuffer resultBuffer = new StringBuffer();
        for(String key:mIatResults.keySet()) {
            resultBuffer.append(mIatResults.get(key));
        }

        textView_content.setText(resultBuffer.toString());
    }

}
