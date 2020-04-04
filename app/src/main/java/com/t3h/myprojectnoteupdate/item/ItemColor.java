package com.t3h.myprojectnoteupdate.item;

import android.support.annotation.DrawableRes;

public class ItemColor {
    private @DrawableRes int color;
    private String colorName;

    public ItemColor(@DrawableRes int color, String colorName) {
        this.color = color;
        this.colorName = colorName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
