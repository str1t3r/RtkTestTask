package ru.rakovsky.rtk.rtktesttask;

/**
 *
 * @author str1t
 */
public class Color {
    private String name;
    private String colorNumber;
    
    public static final String NAME_COLUMN = "name";
    public static final String NUMBER_COLUMN = "color_number";
    public static final String TABLE_NAME = "TSVETA";
    
    public Color(String name, String colorNumber) {
        this.name = name;
        this.colorNumber = colorNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public String getColorNumber() {
        return colorNumber;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setColorNumber(String colorNumber) {
        this.colorNumber = colorNumber;
    }
}
