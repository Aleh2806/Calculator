package aleh.ahiyevich.calculator.calculator.model;

import java.util.List;

// Описываем интерфейс, какие методы он должен имплементировать
public interface ThemeRepository {

    // Получить сохраненную тему
    Theme getSavedTheme();

    // Сохранить тему
    void saveTheme(Theme theme);

    // Получит весь список тем
    List<Theme> getAll();

}
