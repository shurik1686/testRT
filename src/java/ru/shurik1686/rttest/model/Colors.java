package ru.shurik1686.rttest.model;

/**
 *
 * @author pop_av
 */
public class Colors {

    // Поле color_number
    private final String colorNumder;

    // Поле name
    private final String nameColor;

    public Colors(String colorNumder, String nameColor) {
        this.colorNumder = colorNumder;
        this.nameColor = nameColor;
    }

    public String getColorNumder() {
        return colorNumder;
    }

    public String getNameColor() {
        return nameColor;
    }

}
