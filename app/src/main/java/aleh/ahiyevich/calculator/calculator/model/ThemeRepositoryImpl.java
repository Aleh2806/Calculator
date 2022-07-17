package aleh.ahiyevich.calculator.calculator.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

public class ThemeRepositoryImpl implements ThemeRepository {

    // Создаем экземпляр класса ThemeRepository
    private static ThemeRepository INSTANCE;

    private final SharedPreferences preferences;
    private static final String KEY_THEME = "KEY_THEME";

    // Создаем конструктор, в агрументы передаем контекст
    private ThemeRepositoryImpl(Context context) {
        preferences = context.getSharedPreferences("themes.xml", Context.MODE_PRIVATE);

    }

    // Получаем экземпляр класса ThemeRepository
    public static ThemeRepository getINSTANCE(Context context) {
        // Если экземпляр еще не создан(равен null), то создаем его с имеплементирванными методами
        if (INSTANCE == null) {
            INSTANCE = new ThemeRepositoryImpl(context);
        }
        return INSTANCE;
    }


    // Реализация метода получения сохраненной темы
    @Override
    public Theme getSavedTheme() {
        String savedKey = preferences.getString(KEY_THEME, Theme.THEME_ONE.getKey());

        for (Theme theme : Theme.values()) {
            if (theme.getKey().equals(savedKey)) {
                return theme;
            }
        }

        return Theme.THEME_ONE;
    }


    // Реализация метода сохранения темы
    @Override
    public void saveTheme(Theme theme) {
        preferences.edit()
                .putString(KEY_THEME, theme.getKey())
                .apply();
    }

    // Реализация метода получения всего списка тем
    @Override
    public List<Theme> getAll() {
        return Arrays.asList(Theme.values());
    }
}
