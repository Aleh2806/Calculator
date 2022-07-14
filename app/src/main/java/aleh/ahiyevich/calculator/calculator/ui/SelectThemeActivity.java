package aleh.ahiyevich.calculator.calculator.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import aleh.ahiyevich.calculator.R;
import aleh.ahiyevich.calculator.calculator.model.Theme;
import aleh.ahiyevich.calculator.calculator.model.ThemeRepository;
import aleh.ahiyevich.calculator.calculator.model.ThemeRepositoryImpl;


public class SelectThemeActivity extends AppCompatActivity {

    public static final String EXTRA_THEME = "EXTRA_THEME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);

        ThemeRepository themeRepository = ThemeRepositoryImpl.getINSTANCE(this);

        List<Theme> themes = themeRepository.getAll();

        LinearLayout container = findViewById(R.id.container);

        Intent intent = getIntent();

        Theme selectedTheme = (Theme) intent.getSerializableExtra(EXTRA_THEME);

        for (Theme theme : themes) {
            // Преобразуем xml в view
            View itemView = getLayoutInflater().inflate(R.layout.item_theme, container, false);
            // Ищем во View itemView вьюшку с именем title, сохраняем результат в переменной типа TextView
            TextView title = itemView.findViewById(R.id.title);
            // Выставляем заголовок
            title.setText(theme.getTitle());
            // Ищем View с идентификатором theme_card
            CardView cardView = itemView.findViewById(R.id.theme_card);
            // Устанавливаем кликер на всю CardView
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent data = new Intent();
                    data.putExtra(EXTRA_THEME, theme);

                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
            });

            // Ищем галочку checked
            ImageView checked = itemView.findViewById(R.id.checked);

            // Если текущая тема эквивалентна выбранной то показывем галочку
            if (theme.equals(selectedTheme)) {
                checked.setVisibility(View.VISIBLE);
            } else { // В противном случае нет
                checked.setVisibility(View.GONE);
            }

            // Добавляем вью в LinerLayout
            container.addView(itemView);
        }


        findViewById(R.id.button_site).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browseIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.ru"));
                startActivity(Intent.createChooser(browseIntent, null));
            }
        });
    }
}