package com.example.jessicaxu.calorieconverter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import com.example.jessicaxu.calorieconverter.MainActivity;

/**
 * Created by jessicaxu on 2/5/16.
 */
public class Weight extends Activity {


    EditText weight_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_input);

        weight_num = (EditText) findViewById(R.id.weight);
    }

    public void clickToActivityConverter (View view) {
        Intent get_weight = new Intent(this, MainActivity.class);

        String poop = "" + weight_num.getText().toString();
        get_weight.putExtra("weight", poop);
        startActivity(get_weight);
    }

    public void clickToActivityConverterNoWeight (View view) {
        Intent get_weight = new Intent(this, MainActivity.class);
        get_weight.putExtra("weight", "150");
        startActivity(get_weight);
    }




}
