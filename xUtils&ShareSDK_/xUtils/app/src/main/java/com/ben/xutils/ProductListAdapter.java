package com.ben.xutils;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/3/23 0023.
 */
public class ProductListAdapter extends BaseAdapter{

    private Context context;
    private List<product> products;
    public ProductListAdapter(Context context,List<product> products)
    {
        this.context = context;
        this.products = products;
    }

    public List<product> getProducts() {
        return products;
    }

    public void setProducts(List<product> products) {
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/x-utils";
    BitmapUtils bitmapUtils = new BitmapUtils(context,path);


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.product_list_item,null);
            vh = new ViewHolder();
            vh.textView_name = (TextView) convertView.findViewById(R.id.textView_name);
            vh.textView_price = (TextView) convertView.findViewById(R.id.textView_price);
            vh.textView_discount = (TextView) convertView.findViewById(R.id.textView_discount);
            vh.imageView_product_image = (ImageView) convertView.findViewById(R.id.imageView_product_image);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        product p = products.get(position);
        vh.textView_name.setText(p.getName());
        vh.textView_price.setText("¥"+Math.round((p.getPrice()*(p.getDiscount()/10)*100.00))/100/00);
        vh.textView_discount.setText(String.valueOf(p.getDiscount())+"折");
        //用于缓存图片
//        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/x-utils";
//        BitmapUtils bitmapUtils = new BitmapUtils(context,path);
//        bitmapUtils.configDefaultBitmapMaxSize(100,100);
        bitmapUtils.configDefaultBitmapMaxSize(100,100);
        bitmapUtils.display(vh.imageView_product_image,p.getPic().getFileUrl(context));

        return convertView;

    }
    static class ViewHolder{
        TextView textView_name,textView_price,textView_discount;
        ImageView imageView_product_image;
    }
}
