package com.sobonus.hackaton.Country;

/**
 * Created by muhamad.kamal on 2/6/2015.
 */
public class CountryItem {
    private String countryName;
    private boolean isSection;

    public CountryItem(String countryName, boolean isSection) {
        this.countryName = countryName;
        this.isSection = isSection;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public boolean isSection() {
        return isSection;
    }

    public void setSection(boolean isSection) {
        this.isSection = isSection;
    }
}
