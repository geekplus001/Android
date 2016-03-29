package com.ben.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/*
aidl方式是非线程安全的，支持并发处理
绑定服务
通过绑定服务来实现功能的步骤
1、客户端通过bindService方法来绑定一个服务对象，如果绑定成功，会回调用ServiceConnection接口方法onServiceConnected
2、通过Service组件来暴露业务接口
3、服务端通过创建一个*.aidl（安卓定义语言）可以被客户端调用的业务接口
一个aidl文件：（1）不能有修饰符，类似接口的写法
（2）支持的类型有：八种基本数据类型，String，CharSequence，List<String>，Map，自定义类型
自定义类型：
实现Parcelable接口
定义一个aidl文件声明该类型parcelable Person
在其他aidl文件中使用，必须要使用import
4、服务端需要提供一个业务接口的实现类，通常我们会extends Stub类
5、通过Service的onBind方法返回被绑定的业务对象
6、客户端如果绑定成功，就可以像调用自己的方法一样调用远程的业务对象方法


stared启动的服务会长期存在，只要不停
绑定启动的服务通常会在解绑的时候停止
所以通常使用技巧：先利用started后bind
 */
public class MyBoundService extends Service {
    public MyBoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new CatImpl();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
