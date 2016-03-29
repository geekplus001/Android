package com.ben.sensor;

/**
 * Created by Administrator on 2016/3/22 0022.
 * 小方块对象
 */
public class Rect {

    float left = 100,top = 100,right = 100,bottom = 100;
    private int width,height;

    public Rect(int width,int height) {
        this.width = width;
        this.height = height;
        left = (width-right)/2;
        top = (height-bottom)/2;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {//不能出屏幕
        this.left -= left;
        if(this.left<=0)
        {
            this.left = 0;
        }
        else if(this.left>=width-this.right)
        {
            this.left = width - this.right;
        }
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {//不能出屏幕
        this.top += top;
        if(this.top<=0)
        {
            this.top = 0;
        }
        else if(this.top>=height-this.bottom)
        {
            this.top = height - this.bottom;
        }
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }
}
