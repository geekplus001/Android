package com.ben.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{

    private MyMenuListener myMenuListener;



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myMenuListener = (MyMenuListener) activity;
    }


    public MenuFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_menu, container, false);
        view.findViewById(R.id.button6_news).setOnClickListener(this);
        view.findViewById(R.id.button7_musics).setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button6_news:
                myMenuListener.changeValue("news");
                break;
            case R.id.button7_musics:
                myMenuListener.changeValue("musics");
                break;
        }
    }

    public static interface MyMenuListener{
        public void changeValue(String value);
    }
}
