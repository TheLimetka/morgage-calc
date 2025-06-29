package com.example.mortageCalculator.model;

import lombok.Getter;
import lombok.Setter;

public class MortageInput {

    @Getter
    @Setter
    private double loanAmount;
    @Getter
    @Setter
    private double interestRate;
    @Getter
    @Setter
    private double paymentTime;

}
