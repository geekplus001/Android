package com.ben.xutils;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/3/23 0023.
 */
public class product extends BmobObject {
    private String name;
    private BmobFile pic;
    private double price;
    private double discount;

    public product() {
    }

    public product(String name, BmobFile pic, double price, double discount) {
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BmobFile getPic() {
        return pic;
    }

    public void setPic(BmobFile pic) {
        this.pic = pic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "product{" +
                "name='" + name + '\'' +
                ", pic=" + pic +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
