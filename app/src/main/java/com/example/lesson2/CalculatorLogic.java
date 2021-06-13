package com.example.lesson2;

import android.annotation.SuppressLint;
import android.content.Context;


public class CalculatorLogic {

    private int firstArg;
    private int secondArg;

    private final Context context;

    private State state;

    private int actionUserSelected;

    StringBuilder stock = new StringBuilder();

    private enum State {
        firstState,
        secondState,
        operationSelected,
        resultState,
    }

    public CalculatorLogic(Context context) {
        this.context = context;
        state = State.firstState;
    }

    public void reactionClickButtonNumbers(int buttonID){

        if (state == State.resultState) {
            state = State.firstState;
            stock.setLength(0);
        }

        if (state == State.operationSelected) {
            state = State.secondState;
            stock.setLength(0);
        }

        switch (buttonID){
            case R.id.zero:
                if(stock.length() != 0){
                    stock.append(context.getString(R.string._0));
                }
                break;
            case R.id.one:
                stock.append(context.getString(R.string._1)); // такое себе
                break;
            case R.id.two:
                stock.append(context.getString(R.string._2));
                break;
            case R.id.three:
                stock.append(context.getString(R.string._3));
                break;
            case R.id.four:
                stock.append(context.getString(R.string._4));
                break;
            case R.id.five:
                stock.append(context.getString(R.string._5));
                break;
            case R.id.six:
                stock.append(context.getString(R.string._6));
                break;
            case R.id.seven:
                stock.append(context.getString(R.string._7));
                break;
            case R.id.eight:
                stock.append(context.getString(R.string._8));
                break;
            case R.id.nine:
                stock.append(context.getString(R.string._9));
                break;
        }
    }

    public void reactionClickButtonActions(int actionsID) {

        if (actionsID == R.id.equally && state == State.secondState && stock.length() > 0) {
            secondArg = Integer.parseInt(stock.toString());
            state = State.resultState;
            stock.setLength(0);
            switch (actionUserSelected) {
                case R.id.plus:
                    stock.append(firstArg + secondArg);
                    break;
                case R.id.minus:
                    stock.append(firstArg - secondArg);
                    break;
                case R.id.multiply:
                    stock.append(firstArg * secondArg);
                    break;
                case R.id.division:
                    stock.append(firstArg / secondArg);
                    break;
                case R.id.percent:
                    stock.append(firstArg % secondArg);
                    break;
            }
        } else if (stock.length() > 0 && state == State.firstState && actionsID != R.id.equally) {
            firstArg = Integer.parseInt(stock.toString());
            state = State.operationSelected;
            actionUserSelected = actionsID;
        }
    }

    public String getText() {
        StringBuilder stockText = new StringBuilder();
        switch (state) {
            default:
                return stock.toString();
            case operationSelected:
                return stockText.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .toString();
            case secondState:
                return stockText.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(stock)
                        .toString();
        }
    }

    public String getResult(){
        return stock.toString();
    }

    private char getOperationChar() {
        switch (actionUserSelected) {
            case R.id.plus:
                return '+';
            case R.id.minus:
                return '-';
            case R.id.multiply:
                return 'x';
            case R.id.division:
                return '/';
            case R.id.percent:
                return '%';
            case R.id.point:
            default:
                return '.';
        }
    }

    public void clear() {
        state = State.firstState;
        stock.setLength(0);
    }

}