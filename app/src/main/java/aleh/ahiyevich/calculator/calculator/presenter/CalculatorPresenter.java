package aleh.ahiyevich.calculator.calculator.presenter;

import android.view.View;

import java.text.DecimalFormat;

import aleh.ahiyevich.calculator.calculator.model.Calculator;
import aleh.ahiyevich.calculator.calculator.model.Operator;
import aleh.ahiyevich.calculator.calculator.ui.CalculatorActivity;
import aleh.ahiyevich.calculator.calculator.ui.CalculatorView;

public class CalculatorPresenter {

    // Создаем переменные интрефейсов типов CalculatorView и Calculator,чтобы обьеденить их
    // в Presenter
    private final CalculatorView view;
    private final Calculator calculator;

    // Создаем формат вывода результата на экран
    private final DecimalFormat formatter = new DecimalFormat("#.##");


    // Создаем переменные для арументов и операторов
    private double argOne;
    private Double argTwo;
    private Operator selectedOperator;


    // Созадем конструктор
    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }


    // Описывваем метод, который отвечает за нажатие на цифровые кнопки и вывод результата на экран
    public void onDigitPressed(int digit) {

        if (argTwo == null) {
            argOne = argOne * 10 + digit;
            showFormatted(argOne);
        } else {
            argTwo = argTwo * 10 + digit;
            showFormatted(argTwo);
        }
    }

    // Описывваем метод, который отвечает за нажатие на кнопки операторов и вывод результата на экран
    public void onOperatorPressed(Operator operator) {

        if (selectedOperator != null) {
            argOne = calculator.perform(argOne, argTwo, selectedOperator);
            showFormatted(argOne);
        }
        argTwo = 0.0;
        selectedOperator = operator;
    }

    //TODO: Опсиать метод для обратотки нажатия кнопки Точка
    public void onDotPressed() {

    }

    // Описывваем метод, который отвечает за нажатие на кнопку равно и вывод результата на экран
    public void onEqualsPressed() {
        double resultEquals = calculator.perform(argOne, argTwo, selectedOperator);
        showFormatted(resultEquals);
    }

    // Описываем метод форматирования вывода на экран результата
    public void showFormatted(double value) {
        view.showResult(formatter.format(value));
    }
}
