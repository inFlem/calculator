package com.example.lesson2;

public class CalculatorLogic {

    private int firstArg;
    private int secondArg;

    private State state;

    private int actionUserSelected;

    StringBuilder stock = new StringBuilder();

    private enum State {
        firstState,
        secondState,
        operationSelected,
        resultState,
    }

    public CalculatorLogic() {
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
                    stock.append("0");
                }
                break;
            case R.id.one:
                stock.append("1");
                break;
            case R.id.two:
                stock.append("2");
                break;
            case R.id.three:
                stock.append("3");
                break;
            case R.id.four:
                stock.append("4");
                break;
            case R.id.five:
                stock.append("5");
                break;
            case R.id.six:
                stock.append("6");
                break;
            case R.id.seven:
                stock.append("7");
                break;
            case R.id.eight:
                stock.append("8");
                break;
            case R.id.nine:
                stock.append("9");
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
            case resultState:
                return stockText.append(firstArg).append(' ')
                        .append(getOperationChar())
                        .append(' ')
                        .append(secondArg)
                        .append(" = ")
                        .append(stock.toString())
                        .toString();
        }
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