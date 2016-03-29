package com.ben.canvas_drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2016/2/29 0029.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private MyThread thread;

    public MySurfaceView(Context context) {
        super(context);
        holder = this.getHolder();
        holder.addCallback(this);
    }

    class MyThread implements Runnable{

        private SurfaceHolder holder;
        public boolean isRun;
        public MyThread(SurfaceHolder holder)
        {
            this.holder = holder;
            isRun = true;
        }


        @Override
        public void run() {
            int count = 0;
            Canvas canvas = null;
            while(isRun)
            {

                try {
                    synchronized (holder)
                    {
                        canvas = holder.lockCanvas();
                        canvas.drawColor(Color.WHITE);
                        Paint paint = new Paint();
                        paint.setColor(Color.RED);
                        paint.setAntiAlias(true);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawRect(10, 10, 100, 100, paint);
                        paint.setTextSize(20);
                        canvas.drawText("当前是第" + (count++) + "秒", 10, 150, paint);
                        Thread.sleep(1000);
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MyThread(holder);
        thread.isRun = true;
        new Thread(thread).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.isRun = false;
    }
}
