package com.sobonus.hackaton.Model;

/**
 * Created by muhamad.kamal on 14/6/2015.
 */
public class RecipientModel {
    private String Name;
    private String Location;

    public RecipientModel(String name, String location) {
        Name = name;
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
