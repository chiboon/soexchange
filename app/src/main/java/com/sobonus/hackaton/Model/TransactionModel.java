package com.sobonus.hackaton.Model;

import com.parse.ParseUser;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by muhamad.kamal on 13/6/2015.
 */
public class TransactionModel implements Serializable{
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private String bankAccName;
    private String bankAccNo;
    private String bankName;
    private double conversionRate;
    private String date;
    private String time;
    private int status;
    private ParseUser receiver;
    private ParseUser sender;
    private double toAmount;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBankAccName() {
        return bankAccName;
    }

    public void setBankAccName(String bankAccName) {
        this.bankAccName = bankAccName;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ParseUser getReceiver() {
        return receiver;
    }

    public void setReceiver(ParseUser receiver) {
        this.receiver = receiver;
    }

    public ParseUser getSender() {
        return sender;
    }

    public void setSender(ParseUser sender) {
        this.sender = sender;
    }

    public double getToAmount() {
        return toAmount;
    }

    public void setToAmount(double toAmount) {
        this.toAmount = toAmount;
    }
}
