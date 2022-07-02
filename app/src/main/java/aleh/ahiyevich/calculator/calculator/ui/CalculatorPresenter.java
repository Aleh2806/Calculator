package aleh.ahiyevich.calculator.calculator.ui;

import java.text.DecimalFormat;

import aleh.ahiyevich.calculator.calculator.model.Calculator;
import aleh.ahiyevich.calculator.calculator.model.Operator;

public class CalculatorPresenter {

    private CalculatorView view;
    private Calculator calculator;
    private DecimalFormat formatter = new DecimalFormat();

    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;
    private double resultEquals;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    public void onDigitPressed(int digit) {

        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            showFormatted(argOne);

        } else {
            argTwo = argTwo * 10 + digit;
            showFormatted(argTwo);

        }


    }


    public void onOperatorPressed(Operator operator) {

        if (selectedOperator != null) {
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
        }
        argTwo = 0.0;
        selectedOperator = operator;
    }


    public void onDotPressed(Character dot) {
    }


    public void onEqualsPressed() {
        resultEquals = calculator.perform(argOne, argTwo, selectedOperator);
        showFormatted(resultEquals);
    }

    public void showFormatted(double value){
        view.showResult(formatter.format(value));
    }
}
