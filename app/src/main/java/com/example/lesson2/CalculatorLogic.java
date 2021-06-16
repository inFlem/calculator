package com.example.lesson2;

public class CalculatorLogic {

    private int firstArg;

    private State state;

    private int actionUserSelected;

    private Operation operation = null;

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

    public void reactionClickButtonNumbers(int number){

        if (state == State.resultState) {
            state = State.firstState;
            stock.setLength(0);
        }

        if (state == State.operationSelected) {
            state = State.secondState;
            stock.setLength(0);
        }

        switch (number){
            case 0:
                if(stock.length() != 0){
                    stock.append("0");
                }
                break;
            case 1:
                stock.append("1");
                break;
            case 2:
                stock.append("2");
                break;
            case 3:
                stock.append("3");
                break;
            case 4:
                stock.append("4");
                break;
            case 5:
                stock.append("5");
                break;
            case 6:
                stock.append("6");
                break;
            case 7:
                stock.append("7");
                break;
            case 8:
                stock.append("8");
                break;
            case 9:
                stock.append("9");
                break;
        }
    }

    public void reactionClickButtonActions(Operation operation) {

        this.operation = operation;
        if (operation == Operation.EQUALLY && state == State.secondState && stock.length() > 0) {
            int secondArg = Integer.parseInt(stock.toString());
            state = State.resultState;
            stock.setLength(0);
            switch (operation) {
                case PLUS:
                    stock.append(firstArg + secondArg);
                    break;
                case MINUS:
                    stock.append(firstArg - secondArg);
                    break;
                case MULTIPLY:
                    stock.append(firstArg * secondArg);
                    break;
                case DIVISION:
                    stock.append(firstArg / secondArg);
                    break;
                case PERCENT:
                    stock.append(firstArg % secondArg);
                    break;
            }
        } else if (stock.length() > 0 && state == State.firstState && operation != Operation.EQUALLY) {
            firstArg = Integer.parseInt(stock.toString());
            state = State.operationSelected;
           // actionUserSelected = actionsID;
        }
    }

    public String getText() {
        StringBuilder stockText = new StringBuilder();
        switch (state) {
            default:
                return stock.toString();
            case operationSelected:
                return stockText.append(firstArg).append(' ')
                        .append(operation.values())
                        .toString();
            case secondState:
                return stockText.append(firstArg).append(' ')
                        .append(operation.values())
                        .append(' ')
                        .append(stock)
                        .toString();
        }
    }

    public String getResult(){
        return stock.toString();
    }



    public void clear() {
        state = State.firstState;
        stock.setLength(0);
    }

}