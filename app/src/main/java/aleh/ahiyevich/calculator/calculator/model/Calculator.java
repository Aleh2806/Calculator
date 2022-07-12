package aleh.ahiyevich.calculator.calculator.model;

// Создаем интерфейс в котором определяем метод для выполнения операции
// с выбранными цифрами и операторами
public interface Calculator {
    double perform(double argOne, double argTwo, Operator operator);
}
