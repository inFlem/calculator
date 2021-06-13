package com.example.lesson2;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textResult;
    private TextView textCount;

    private CalculatorLogic calculatorLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResult = findViewById(R.id.etNum);
        textCount = findViewById(R.id.etNum2);

        int[] numbersID = new int[]{
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

        int[] actionsID = new int[]{
                R.id.plus,
                R.id.minus,
                R.id.multiply,
                R.id.division,
                R.id.percent,
                R.id.clear,
                R.id.changePosition,
                R.id.point,
        };

        calculatorLogic = new CalculatorLogic(this);

        View.OnClickListener clickButtonNumbers = v -> {
            calculatorLogic.reactionClickButtonNumbers(v.getId());
            textCount.setText(calculatorLogic.getText());
        };

        View.OnClickListener clickButtonActions = v -> {
            calculatorLogic.reactionClickButtonActions(v.getId());
            textCount.setText(calculatorLogic.getText());

        };

        findViewById(R.id.equally).setOnClickListener(v -> {
            calculatorLogic.reactionClickButtonActions(v.getId());
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
}

