package com.ben.autocompletetextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.city_name,android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(adapter);
    }
}
