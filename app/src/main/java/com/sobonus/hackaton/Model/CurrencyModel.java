package com.sobonus.hackaton.Model;

/**
 * Created by muhamad.kamal on 13/6/2015.
 */
public class CurrencyModel {
    private String baseName;
    private String baseCode;
    private boolean isSection;

    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public CurrencyModel(String baseName, String baseCode) {

        this.baseName = baseName;
        this.baseCode = baseCode;
    }

    public boolean isSection() {
        return isSection;
    }

    public void setIsSection(boolean isSection) {
        this.isSection = isSection;
    }

    public CurrencyModel(String baseName, String baseCode, boolean isSection) {
        this.baseName = baseName;
        this.baseCode = baseCode;
        this.isSection = isSection;
    }
}
