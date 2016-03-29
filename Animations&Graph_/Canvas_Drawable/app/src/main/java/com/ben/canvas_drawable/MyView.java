package com.ben.canvas_drawable;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
/**
 * Created by Administrator on 2016/2/29 0029.
 * 自定义组件
 */
public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    //事件方法  会在组件加载时候调用

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置背景颜色为白色
        canvas.drawColor(Color.WHITE);
        //创建画笔
        Paint paint = new Paint();
        //去锯齿
        paint.setAntiAlias(true);
        //设置paint的颜色
        paint.setColor(Color.RED);
        //设置paint的style为STROKE：空心
        paint.setStyle(Paint.Style.STROKE);
        //设置paint的外框宽度
        paint.setStrokeWidth(3);
        //画一个空心圆形
        //参数：圆心x，y，半径r，paint
        canvas.drawCircle(40, 40, 30, paint);
        //画一个空心正方形
        canvas.drawRect(10, 170, 70, 250, paint);
        //画一个空心椭圆
        RectF re = new RectF(10,220,70,250);
        canvas.drawOval(re,paint);
        //画一个空心三角形
        Path path = new Path();
        path.moveTo(10,330);
        path.lineTo(70, 330);
        path.lineTo(40, 270);
        path.close();
        canvas.drawPath(path,paint);
        //画一个空心梯形
        Path path1 = new Path();
        path1.moveTo(10, 410);
        path1.lineTo(70, 410);
        path1.lineTo(55, 350);
        path1.lineTo(25, 350);
        path1.close();
        canvas.drawPath(path1, paint);


        //设置paint的style的为实心
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        //画一个实心圆
        canvas.drawCircle(120,40,30,paint);

        //设置渐变色
        Shader shader = new LinearGradient(0,0,100,100,new int[]{
           Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW},null,
                Shader.TileMode.MIRROR);
        paint.setShader(shader);
        //画一个渐变色圆
        canvas.drawCircle(200,40,30,paint);
        //写字
        paint.setTextSize(24);
        canvas.drawText("圆形",240,50,paint);
        canvas.drawText("正方形",240,120,paint);
        canvas.drawText("长方形",240,190,paint);
    }
}
