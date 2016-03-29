package com.ben.sharesdk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mob.tools.utils.UIHandler;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void shareClick(View view)
    {
        showShare();
    }

    public void xinlangClick(View view)
    {
        Platform xinlang= ShareSDK.getPlatform(this, SinaWeibo.NAME);
        xinlang.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        xinlang.authorize();
    }

    private void authorize(Platform plat) {
        if (plat == null) {
//            popupOthers();
            return;
        }
//判断指定平台是否已经完成授权
//        if(plat.isAuthValid()) {
//            String userId = plat.getDb().getUserId();
//            if (userId != null) {
//                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
//                login(plat.getName(), userId, null);
//                return;
//            }
//        }
//        plat.setPlatformActionListener(this);
//        // true不使用SSO授权，false使用SSO授权
        plat.SSOSetting(true);
        //获取用户资料
        plat.showUser(null);
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://user.qzone.qq.com/877048930/infocenter?ptsig=cU09pZRK*i1cFFIGvBSyicW6uyiT4X1AYhZv6rMlg18_");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片

        oks.setImageUrl("http://img2.imgtn.bdimg.com/it/u=2459549116,182359318&fm=21&gp=0.jpg");


        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://user.qzone.qq.com/877048930/infocenter?ptsig=cU09pZRK*i1cFFIGvBSyicW6uyiT4X1AYhZv6rMlg18_");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://user.qzone.qq.com/877048930/infocenter?ptsig=cU09pZRK*i1cFFIGvBSyicW6uyiT4X1AYhZv6rMlg18_");

// 启动分享GUI
        oks.show(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK();
    }
}
