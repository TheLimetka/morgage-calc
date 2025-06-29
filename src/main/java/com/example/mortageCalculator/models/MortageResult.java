package com.example.mortageCalculator.model;

import lombok.Getter;
import lombok.Setter;

public class MortageResult {

    @Getter
    @Setter
    private double finalAmount;
    @Getter
    @Setter
    private double overpay;
    @Getter
    @Setter
    private double monthlyPayment;

    public MortageResult(double finalAmount, double overpay, double monthlyPayment){
        this.finalAmount = finalAmount;
        this.monthlyPayment = monthlyPayment;
        this.overpay = overpay;
    }
}
