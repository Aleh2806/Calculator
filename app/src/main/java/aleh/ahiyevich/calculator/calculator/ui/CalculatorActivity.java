package aleh.ahiyevich.calculator.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import aleh.ahiyevich.calculator.R;
import aleh.ahiyevich.calculator.calculator.model.CalculatorImpl;
import aleh.ahiyevich.calculator.calculator.model.Operator;
import aleh.ahiyevich.calculator.calculator.presenter.CalculatorPresenter;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView resultTxt;

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        resultTxt = findViewById(R.id.result);

        presenter = new CalculatorPresenter(CalculatorActivity.this, new CalculatorImpl());

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


        View.OnClickListener digitOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDigitPressed(digits.get(view.getId()));
            }
        };


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


        Map<Integer, Operator> operators = new HashMap<>();
        operators.put(R.id.key_plus, Operator.PLUS);
        operators.put(R.id.key_minus, Operator.MINUS);
        operators.put(R.id.key_mult, Operator.MULT);
        operators.put(R.id.key_div, Operator.DIV);


        View.OnClickListener operatorOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onOperatorPressed(operators.get(view.getId()));
            }
        };

        findViewById(R.id.key_plus).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_mult).setOnClickListener(operatorOnClickListener);
        findViewById(R.id.key_div).setOnClickListener(operatorOnClickListener);


        Map<Integer, Character> dot = new HashMap<>();
        dot.put(R.id.key_dot, '.');


        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onDotPressed(dot.get(view.getId()));
            }
        });


        findViewById(R.id.key_equals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEqualsPressed();
            }
        });


    }


    @Override
    public void showResult(String result) {
        resultTxt.setText(result);
    }
}