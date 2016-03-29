package com.ben.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2015/11/6 0006.
 */
public class MyView extends View {

    private int textColor;
    private float textSize;
    private String text;
    private Paint paint;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        //获取配置文件中的属性值
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        textColor = array.getColor(R.styleable.MyView_textColor, 0XFFFFFF);
        textSize = array.getDimension(R.styleable.MyView_textSize, 24);
        text = array.getString(R.styleable.MyView_text);
        System.out.println("1111111111111111");
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

   /* public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
      //  super(context, attrs, defStyleAttr, defStyleRes);
    }*/


    //视图的绘制事件方法
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(textColor);

        System.out.println("草草草凹槽草草草");
        System.out.println(textColor);
        paint.setTextSize(textSize);

        canvas.drawText(text,10,10,paint);
    }
}
