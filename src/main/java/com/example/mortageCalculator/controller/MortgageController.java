package com.example.mortageCalculator.controller;

import com.example.mortageCalculator.model.MortageInput;
import com.example.mortageCalculator.model.MortageResult;
import com.example.mortageCalculator.service.MortgageCalculatorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/mortgage")
public class MortgageController {

    @Autowired
    private MortgageCalculatorService mortgageService;

    @PostMapping("/calculate")
    public ResponseEntity<MortageResult> calculateMortgage(@RequestBody MortageInput input) {
        try {
            MortageResult result = mortgageService.calculateMortgage(input);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/calculate")
    public ResponseEntity<MortageResult> calculateMortgageGet(
            @RequestParam double loanAmount,
            @RequestParam double interestRate,
            @RequestParam double paymentTime) {
        
        try {
            MortageInput input = new MortageInput();
            input.setLoanAmount(loanAmount);
            input.setInterestRate(interestRate);
            input.setPaymentTime(paymentTime);
            
            MortageResult result = mortgageService.calculateMortgage(input);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}