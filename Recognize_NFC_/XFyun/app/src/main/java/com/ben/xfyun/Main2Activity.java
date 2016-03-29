package com.ben.xfyun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText editText_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText_content = (EditText) findViewById(R.id.editText_content);
        //初始化
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=56f7a051");
    }

    public void startClick(View view)
    {
        //1.创建 SpeechSynthesizer 对象, 第二个参数：本地合成时传 InitListener
        SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer(this, null);
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录13.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "vixm"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置） ，保存在“./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式，如果不需要保存合成音频，注释该行代码
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");

        //3.开始合成
        mTts.startSpeaking(editText_content.getText().toString(), mSynListener);
    }

    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener(){
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {

        }
        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在
        //开始播放
        public void onSpeakBegin() {

        }

        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        //暂停播放
        public void onSpeakPaused() {}
        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文
        public void onSpeakProgress(int percent, int beginPos, int endPos) {

        }
        //恢复播放回调接口
        public void onSpeakResumed() {

        }
        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {

        }
    };
}
