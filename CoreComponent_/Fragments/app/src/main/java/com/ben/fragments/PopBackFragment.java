package com.ben.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PopBackFragment extends Fragment {

//    String title;

    public PopBackFragment() {
        // Required empty public constructor

    }

//    public PopBackFragment(String title) {
//        // Required empty public constructor
//        this.title = title;
//    }

    /*
    旋转会重新生成activity 所以用标准传参方法
    Fragment的标准传递参数方法
     */
    public static PopBackFragment getInstance(String title)
    {
        PopBackFragment p  = new PopBackFragment();
        Bundle b = new Bundle();
        b.putString("title",title);
        p.setArguments(b);
        return p;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pop_back, container, false);
        TextView tv = (TextView) view.findViewById(R.id.textView_text);
        tv.setText(getArguments().getString("title"));
        return view;
    }


}
