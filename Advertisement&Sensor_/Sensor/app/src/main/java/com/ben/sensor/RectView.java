package com.ben.sensor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
/**
 * Created by Administrator on 2016/3/22 0022.
 */
public class RectView extends View {

    Rect rect;
    private int width,height;
    Paint paint;
    public RectView(Context context,int width,int height) {
        super(context);
        this.width = width;
        this.height = height;

        paint = new Paint();
        paint.setColor(Color.GREEN);
        rect = new Rect(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(rect.getLeft(),rect.getTop(),rect.getLeft()+rect.getRight(),rect.getTop()+rect.getBottom(),paint);
    }
}
