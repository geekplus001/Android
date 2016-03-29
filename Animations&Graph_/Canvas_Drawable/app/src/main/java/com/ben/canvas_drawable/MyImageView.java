package com.ben.canvas_drawable;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Administrator on 2016/2/29 0029.
 */
public class MyImageView extends View {
    public MyImageView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.l);
        canvas.drawBitmap(bitmap,0,0,paint);
    }
}
