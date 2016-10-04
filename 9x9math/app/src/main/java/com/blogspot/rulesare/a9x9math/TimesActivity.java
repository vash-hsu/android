package com.blogspot.rulesare.a9x9math;

public class TimesActivity extends MathActivity {
    // game logic and data structure
    protected int[] prepareBusinessLogic(){
        flag = ran.nextInt(3); // return 0, 1, 2
        intA = ran.nextInt(9)+1;
        intB = ran.nextInt(9)+1;
        int[] dataSet = myGame.mutate(operation, intA, intB, 3, flag);
        answer = dataSet[flag];
        return (dataSet);
    }

    protected void customize_operation(){
        decide_operation("*");
    }
}
