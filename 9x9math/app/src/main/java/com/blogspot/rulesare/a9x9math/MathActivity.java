package com.blogspot.rulesare.a9x9math;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;


public class MathActivity extends AppCompatActivity {

    protected Random ran = new Random();
    protected int flag, intA, intB, answer;
    protected String operation;
    MathGame9x9 myGame = new MathGame9x9();
    protected Button[] buttons = new Button[3];
    protected TextView[] inputUser = new TextView[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        customize_operation();
        prepareUI();
        doSomethingEachRun();
        wait_and_proceed_answer();
    }

    protected void customize_operation()
    {
        decide_operation("*");
    }

    protected void decide_operation(String op){
        operation = op;
    }

    protected void wait_and_proceed_answer(){
        for (int i=0; i<3; i++){
            final Button target_button = buttons[i];
            target_button.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v){
                    Button working = (Button) v;
                    if (Integer.parseInt(working.getText().toString()) == answer) {
                        doSomethingEachRun();
                    }
                    else{
                        working.setTextColor(Color.GRAY);
                        target_button.setClickable(false);
                        String title = getResources().getString(R.string.title_encourage);
                        String message = getResources().getString(R.string.message_encourage);
                        showAlertAndCloseAutomatically(title, message, 750);
                    }
                }
            });
        }
    }

    protected void prepareUI(){
        inputUser[0] = (TextView) findViewById(R.id.textA);
        inputUser[1] = (TextView) findViewById(R.id.textB);
        inputUser[2] = (TextView) findViewById(R.id.textOperator);
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
    }

    protected void doSomethingEachRun(){
        // game logic and data structure
        int[] candidates = prepareBusinessLogic();
        // draw UI
        drawUI(candidates);
    }

    protected void drawUI(int[] dataSet){
        inputUser[0].setText(String.valueOf(intA));
        inputUser[1].setText(String.valueOf(intB));
        inputUser[2].setText(operation);
        for (int i=0; i<3; i++) {
            buttons[i].setText(String.valueOf(dataSet[i]));
            buttons[i].setClickable(true);
            buttons[i].setTextColor(Color.BLUE);
        }
    }

    // game logic and data structure
    protected int[] prepareBusinessLogic(){
        flag = ran.nextInt(3); // return 0, 1, 2
        intA = ran.nextInt(9)+1;
        intB = ran.nextInt(9)+1;
        int[] dataSet = myGame.mutate(operation, intA, intB, 3, flag);
        Log.d("prepareBusinessLogic", String.format("%s: %d %d %d",
                "A, B, Chosen", intA, intB, flag));
        answer = dataSet[flag];
        return (dataSet);
    }

    public void showAlertAndCloseAutomatically(String title, String message, long ms){
        String action2 = getResources().getString(R.string.display_confirm);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this).
                setTitle(title).
                setMessage(message).
                setPositiveButton(action2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //
                    }
                });
        final AlertDialog alert = dialog.create();
        alert.show();
        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                }
            }
        };
        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, ms);
    }
}
