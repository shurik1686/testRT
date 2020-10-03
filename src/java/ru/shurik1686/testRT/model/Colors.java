package ru.shurik1686.testRT.model;

/**
 *
 * @author pop_av
 */
public class Colors {

    // Поле color_number
    String colorNumder;

    // Поле name
    String nameColor;

    public Colors(String colorNumder, String nameColor) {
        this.colorNumder = colorNumder;
        this.nameColor = nameColor;
    }

    public String getColorNumder() {
        return colorNumder;
    }

    public void setColorNumder(String colorNumder) {
        this.colorNumder = colorNumder;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

}
