package com.blogspot.rulesare.a9x9math;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.app.Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // prepare button and activity
        final int[] ready_id = new int[]{
                R.id.button_plus, R.id.button_times,
                R.id.button_minus, R.id.button_divide,
        };
        // those features under constructions
        final int[] notyet_id = new int[]{
                R.id.button_ranking, R.id.button_quit
        };
        // disable those not-yet-implement features
        for (int id : notyet_id){
            Button button = (Button) findViewById(id);
            button.setClickable(false);
            button.setTextColor(Color.GRAY);
        }
        for (int id : ready_id){
            Button button = (Button) findViewById(id);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view){
        Intent intent;
        if (view == findViewById(R.id.button_times))
        {
            intent = new Intent(this, TimesActivity.class);
        }
        else if(view == findViewById(R.id.button_plus))
        {
            intent = new Intent(this, PlusActivity.class);
        }
        else if(view == findViewById(R.id.button_minus))
        {
            intent = new Intent(this, MinusActivity.class);
        }
        else if(view == findViewById(R.id.button_divide))
        {
            intent = new Intent(this, DivideActivity.class);
        }
        else
        {
            return;
        }
        startActivity(intent);
    }
}
