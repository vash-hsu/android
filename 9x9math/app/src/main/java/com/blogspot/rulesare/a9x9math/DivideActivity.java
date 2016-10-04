package com.blogspot.rulesare.a9x9math;

import android.util.Log;

public class DivideActivity extends MathActivity {

    // game logic and data structure
    protected int[] prepareBusinessLogic(){
        flag = ran.nextInt(3); // return 0, 1, 2
        intA = ran.nextInt(9)+1;
        do {
            intB = ran.nextInt(9)+1;
        }while (intB == intA);
        intA = intB * intA;
        int[] dataSet = myGame.mutate("/", intA, intB, 3, flag);
        Log.d("prepareBusinessLogic", String.format("%s: %d %d %d",
                "A, B, Chosen", intA, intB, flag));
        Log.d("prepareBusinessLogic", String.format("%s: %d %d %d",
                "dataSet", dataSet[0], dataSet[1],dataSet[2]));
        answer = dataSet[flag];
        return (dataSet);
    }

    protected void customize_operation(){
        decide_operation("/");
    }
}
