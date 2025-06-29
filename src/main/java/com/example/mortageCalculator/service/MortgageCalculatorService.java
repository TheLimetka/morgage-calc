package com.example.mortageCalculator.service;

import com.example.mortageCalculator.model.MortageInput;
import com.example.mortageCalculator.model.MortageResult;
import org.springframework.stereotype.Service;

@Service
public class MortgageCalculatorService {

    /**
     * Calculate mortgage payment details
     * @param input MortageInput containing loan parameters
     * @return MortageResult with calculated values
     * @throws IllegalArgumentException if input parameters are invalid
     */
    public MortageResult calculateMortgage(MortageInput input) {
        validateInput(input);
        
        double loanAmount = input.getLoanAmount();
        double annualInterestRate = input.getInterestRate();
        double paymentTimeYears = input.getPaymentTime();

        // Convert annual interest rate to monthly decimal rate
        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        
        // Total number of monthly payments
        int totalPayments = (int) (paymentTimeYears * 12);

        double monthlyPayment = calculateMonthlyPayment(loanAmount, monthlyInterestRate, totalPayments);
        
        // Calculate total amount paid over the life of the loan
        double finalAmount = monthlyPayment * totalPayments;
        
        // Calculate overpay (total interest paid)
        double overpay = finalAmount - loanAmount;

        return new MortageResult(
            Math.round(finalAmount * 100.0) / 100.0,
            Math.round(overpay * 100.0) / 100.0,
            Math.round(monthlyPayment * 100.0) / 100.0
        );
    }

    // Private helper methods

    private void validateInput(MortageInput input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.getLoanAmount() <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than 0");
        }
        if (input.getInterestRate() < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        if (input.getPaymentTime() <= 0) {
            throw new IllegalArgumentException("Payment time must be greater than 0");
        }
    }

    private double calculateMonthlyPayment(double loanAmount, double monthlyInterestRate, int totalPayments) {
        if (monthlyInterestRate == 0) {
            // Handle case where interest rate is 0%
            return loanAmount / totalPayments;
        }

        // Calculate monthly payment using mortgage formula:
        // M = P * [r(1+r)^n] / [(1+r)^n - 1]
        double onePlusRate = 1 + monthlyInterestRate;
        double onePlusRatePowN = Math.pow(onePlusRate, totalPayments);
        
        return loanAmount * 
            (monthlyInterestRate * onePlusRatePowN) / 
            (onePlusRatePowN - 1);
    }
}