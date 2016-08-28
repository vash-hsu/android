package com.blogspot.rulesare.a9x9math;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import android.view.View;
import android.util.Log;
import android.os.Handler;

// How do I display an alert dialog on Android?
// http://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android

// Android close dialog after 5 seconds?
// http://stackoverflow.com/questions/14445745/android-close-dialog-after-5-seconds
public class TimesActivity extends AppCompatActivity {

    protected Random ran = new Random();
    protected int flag;
    protected int intA;
    protected int intB;
    protected int answer;
    protected Button[] buttons = new Button[3];
    protected TextView[] inputUser = new TextView[2];

    // game logic and data structure
    private int[] prepareBusinessLogic(){
        flag = ran.nextInt(3); // return 0, 1, 2
        intA = ran.nextInt(9)+1;
        intB = ran.nextInt(9)+1;
        int[] dataSet = _operation(intA, intB, flag);
        Log.d("prepareBusinessLogic", String.format("%s: %d %d %d",
                "A, B, Chosen", intA, intB, flag));
        Log.d("prepareBusinessLogic", String.format("%s: %d %d %d",
                "dataSet", dataSet[0], dataSet[1],dataSet[2]));
        answer = dataSet[flag];
        return (dataSet);
    }

    private void prepareUI(){
        inputUser[0] = (TextView) findViewById(R.id.textA);
        inputUser[1] = (TextView) findViewById(R.id.textB);
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
    }

    private void doSomethingEachRun(){
        // game logic and data structure
        int[] candidates = prepareBusinessLogic();
        // draw UI
        drawUI(candidates);
    }

    private void drawUI(int[] dataSet){
        inputUser[0].setText(String.valueOf(intA));
        inputUser[1].setText(String.valueOf(intB));
        for (int i=0; i<3; i++) {
            buttons[i].setText(String.valueOf(dataSet[i]));
            buttons[i].setClickable(true);
            buttons[i].setTextColor(Color.BLUE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times);
        prepareUI();
        doSomethingEachRun();
        // wait&process answer
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
                        showAlertAndCloseAutomatically("Not Exactly",
                                "Try again. We can make it!",
                                750);
                    }
                }
            });
        }
    }

    public void showAlertAndCloseAutomatically(String title, String message, long ms){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this).
                setTitle(title).
                setMessage(message).
                setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
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

    private int[] _operation(int inputA, int inputB, int offset){
            // prepare data structure for gaming
            int correct = inputA *inputB;
            int[] candidate = new int[3];
            switch (offset) {
                case 0: // right, wrong, wrong
                    candidate[0] = correct;
                    candidate[1] = correct+1;
                    candidate[2] = correct+2;
                    break;
                case 1: // wrong, right, wrong
                    candidate[0] = correct-1;
                    candidate[1] = correct;
                    candidate[2] = correct+1;
                    break;
                default: // case 2:  wrong, wrong, right
                    candidate[0] = correct-2;
                    candidate[1] = correct-1;
                    candidate[2] = correct;
                    break;
            }
            return(candidate);
    }
}
