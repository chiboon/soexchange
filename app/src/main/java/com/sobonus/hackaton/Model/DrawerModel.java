package com.sobonus.hackaton.Model;

/**
 * Created by muhamad.kamal on 7/4/2015.
 */
public class DrawerModel
{
    private String drawerText;
    private String drawerImg;

    public DrawerModel(String drawerText, String drawerImg) {
        this.drawerText = drawerText;
        this.drawerImg = drawerImg;
    }

    public String getDrawerText() {
        return drawerText;
    }

    public void setDrawerText(String drawerText) {
        this.drawerText = drawerText;
    }

    public String getDrawerImg() {
        return drawerImg;
    }

    public void setDrawerImg(String drawerImg) {
        this.drawerImg = drawerImg;
    }
}
