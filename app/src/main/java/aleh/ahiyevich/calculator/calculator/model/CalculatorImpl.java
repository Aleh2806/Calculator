package aleh.ahiyevich.calculator.calculator.model;

public class CalculatorImpl implements Calculator {

    // Опсиываем операции, которые необходимо будет выполнить программе после выбора
    // enum выражения
    @Override
    public double perform(double argOne, double argTwo, Operator operator) {
        switch (operator) {
            case PLUS:
                return argOne + argTwo;
            case MINUS:
                return argOne - argTwo;
            case MULT:
                return argOne * argTwo;
            case DIV:
                return argOne / argTwo;
        }
        return 0.0;
    }
}
