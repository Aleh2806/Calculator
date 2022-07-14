package aleh.ahiyevich.calculator.calculator.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import aleh.ahiyevich.calculator.R;

public enum Theme {

    THEME_ONE(R.style.Theme_MyCalc,R.string.theme_1,"theme_one"),
    THEME_TWO(R.style.Theme_MyCalc_V2,R.string.theme_2,"theme_two"),
    THEME_THREE(R.style.Theme_MyCalc_V3,R.string.theme_3,"theme_three");

    @StyleRes
    private int themeRes;

    @StringRes
    private int title;

    private String key;

    Theme(int themeRes,int title,String key){
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}
