package com.example.lesson2;


import android.annotation.SuppressLint;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private TextView textCount;

    private final static String COUNTERS = "counters";

    private CalculatorLogic calculatorLogic;

    private final int[] numbersID = new int[]{
            R.id.zero,
            R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            R.id.six,
            R.id.seven,
            R.id.eight,
            R.id.nine
    };

    private final int[] actionsID = new int[]{
            R.id.plus,
            R.id.minus,
            R.id.multiply,
            R.id.division,
            R.id.percent,
            R.id.clear,
            R.id.changePosition,
            R.id.point,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.etNum);
        textCount = findViewById(R.id.etNum2);

        calculatorLogic = new CalculatorLogic();

        View.OnClickListener clickButtonNumbers = v -> {
            calculatorLogic.reactionClickButtonNumbers(getNumbersByViewId(v.getId()));
            textCount.setText(calculatorLogic.getText());
        };

        View.OnClickListener clickButtonActions = v -> {
            calculatorLogic.reactionClickButtonActions(getActionByViewId(v.getId()));
            textCount.setText(calculatorLogic.getText());

        };

        findViewById(R.id.equally).setOnClickListener(v -> {
            calculatorLogic.reactionClickButtonActions(getActionByViewId(v.getId()));
            textResult.setText(calculatorLogic.getResult());
            calculatorLogic.clear();
            textCount.setText("");
        });

        for (int i : numbersID) {
            findViewById(i).setOnClickListener(clickButtonNumbers);
        }

        for (int i : actionsID) {
            findViewById(i).setOnClickListener(clickButtonActions);
        }

        findViewById(R.id.clear).setOnClickListener(view -> {
            calculatorLogic.clear();
            textCount.setText(calculatorLogic.getText());
            textResult.setText(calculatorLogic.getText());
        });
    }

    @SuppressLint("NonConstantResourceId")
    Operation getActionByViewId(int action){
        switch (action){
            case R.id.plus:
                return Operation.PLUS;
            case R.id.minus:
                return Operation.MINUS;
            case R.id.multiply:
                return Operation.MULTIPLY;
            case R.id.division:
                return Operation.DIVISION;
            case R.id.point:
                return Operation.POINT;
            case R.id.equally:
                return Operation.EQUALLY;
            case R.id.percent:
                return Operation.PERCENT;
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }

    @SuppressLint("NonConstantResourceId")
    int getNumbersByViewId(int number) {
        switch (number) {
            case R.id.zero:
                return 0;
            case R.id.one:
                return 1;
            case R.id.two:
                return 2;
            case R.id.three:
                return 3;
            case R.id.four:
                return 4;
            case R.id.five:
                return 5;
            case R.id.six:
                return 6;
            case R.id.seven:
                return 7;
            case R.id.eight:
                return 8;
            case R.id.nine:
                return 9;
            default:
                throw new IllegalArgumentException("Unknown symbol");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelable(COUNTERS, calculatorLogic);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorLogic = savedInstanceState.getParcelable(COUNTERS);
        PrintResult();
    }

    private void PrintResult(){
        if (calculatorLogic.getOperation() == Operation.EQUALLY) textResult.setText(calculatorLogic.getResult());
        else textCount.setText(calculatorLogic.getText());
    }
}

