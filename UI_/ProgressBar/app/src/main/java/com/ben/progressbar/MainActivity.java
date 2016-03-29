package com.ben.progressbar;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.view.Window;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置标题栏
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);


        setContentView(R.layout.activity_main);
        //显示进度条
        setProgressBarIndeterminateVisibility(true);
    }
    /*
    显示对话框进度条
     */
    public void showDialogProgress(View v)
    {
        //创建对话框进度条
//        ProgressDialog pd = new ProgressDialog(this);
//        pd.setMax(100);
//        pd.setProgress(30);
////        pd.setIndeterminate(false);
//        pd.setCancelable(true);//是否可以被取消
//        pd.setTitle("下载对话框");
//        pd.setMessage("正在下载...");
//        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        pd.show();



        ProgressDialog pd2 = ProgressDialog.show(this,"download","downloading...",false);

    }
}
