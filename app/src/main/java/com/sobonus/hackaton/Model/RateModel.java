package com.sobonus.hackaton.Model;

/**
 * Created by muhamad.kamal on 13/6/2015.
 */
public class RateModel {
    private String from;
    private String to;
    private double rate;

    public  RateModel(){}
    public RateModel(String from, String to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
