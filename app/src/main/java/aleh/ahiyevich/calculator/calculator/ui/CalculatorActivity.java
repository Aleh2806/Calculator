package aleh.ahiyevich.calculator.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import aleh.ahiyevich.calculator.R;
import aleh.ahiyevich.calculator.calculator.model.CalculatorImpl;
import aleh.ahiyevich.calculator.calculator.model.Operator;
import aleh.ahiyevich.calculator.calculator.presenter.CalculatorPresenter;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    //Создаем переменную типа TextView,чтобы позже найти поле с выводом результата на экране
    private TextView resultView;

    //Создаем переменную типа CalculatorPresenter,чтобы позже можно было обращаться к классу CalculatorPresenter
    private CalculatorPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Сохраняем настройки выбора пользователем темы
        SharedPreferences sharedPreferences = getSharedPreferences("themes.xml", Context.MODE_PRIVATE);
        // Сохраняем дефолтную тему в переменную
        int theme = sharedPreferences.getInt("theme", R.style.Theme_MyCalc);
        // Изменяем тему до отрисовки Активити,т.к. после отрисовки Активити, отрисовка темы невозможна
        setTheme(theme);

        // Отрисываваем Активити
        setContentView(R.layout.activity_calculator);

        // Ищем поле с выводом результата и сохраняем в переменную
        resultView = findViewById(R.id.result);

        // Создаем новый обьект типа CalculatorPresenter, где обьеденяем view и model части приложения
        presenter = new CalculatorPresenter(CalculatorActivity.this, new CalculatorImpl());

        // Помещаем все цифры в Коллекцию Map
        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_0, 0);
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);


        // Вешаем кликер на все кнопки
        View.OnClickListener digitOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };


        // Назначаем каждой цифре кликер
        findViewById(R.id.key_0).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_1).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitOnClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitOnClickListener);

        // Помещаем все операторы в Коллекцию Map
        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_plus, Operator.PLUS);
        operators.put(R.id.key_minus, Operator.MINUS);
        operators.put(R.id.key_mult, Operator.MULT);
        operators.put(R.id.key_div, Operator.DIV);

        // Вешаем кликер на все кнопки
        View.OnClickListener operatorOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };

        // Назначаем каждому оператору кликер
        findViewById(R.id.key_plus).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_mult).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_div).setOnClickListener(operatorOnClickListener);

        // Назначаем кликер точке
        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dot = resultView.getText().toString();
                if (!dot.contains(".")) {
                    dot += ".";
                } else if (dot.contains(".")) {
                    dot = dot;
                }
                showResult(dot);

            }
        });

        // Ищем все темы и сохраняем в переменные
        Button themeOne = findViewById(R.id.theme_1);
        Button themeTwo = findViewById(R.id.theme_2);
        Button themeThree = findViewById(R.id.theme_3);

        // Описываем выбор темы 1
        if (themeOne != null) {
            themeOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Описываем, какую тему необходимо выбрать
                    sharedPreferences.edit()
                            .putInt("theme", R.style.Theme_MyCalc)
                            .commit();

                    recreate(); // пересоздаем Активити
                }
            });
        }

        // Описываем выбор темы 2
        if (themeTwo != null) {
            themeTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Описываем, какую тему необходимо выбрать
                    sharedPreferences.edit()
                            .putInt("theme", R.style.Theme_MyCalc_V2)
                            .commit();

                    recreate(); // пересоздаем Активити

                }
            });
        }

        // Описываем выбор темы 3
        if (themeThree != null) {
            themeThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Описываем, какую тему необходимо выбрать
                    sharedPreferences.edit()
                            .putInt("theme", R.style.Theme_MyCalc_V3)
                            .commit();

                    recreate(); // пересоздаем Активити

                }
            });
        }

        // Вешаем кликкер на кнопку равно и передаем в него метод для вычисления
        findViewById(R.id.key_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEqualsPressed();
            }
        });


    }


    // Переопределяем метод для вывода результата на экран в строковом виде
    @Override
    public String showResult(String result) {
        resultView.setText(result);
        return result;
    }

}

