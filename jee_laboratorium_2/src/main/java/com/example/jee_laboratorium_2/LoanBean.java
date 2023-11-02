package com.example.jee_laboratorium_2;

import java.io.Serializable;

public class LoanBean implements Serializable {
    private Double loanValue = 1000.0;
    private Double loanPercentage = 10.0;
    private Integer loanInstallments = 10;

    public Double getInstallment(){
        Double loanMonthlyPercentage = loanPercentage/12/100;
        return (loanValue * loanMonthlyPercentage)/
                (1 - (1/Math.pow((1+loanMonthlyPercentage), loanInstallments)));
    }

    public Double getLoanValue() {
        return loanValue;
    }

    public void setLoanValue(Double loanValue) {
        this.loanValue = loanValue;
    }

    public Double getLoanPercentage() {
        return loanPercentage;
    }

    public void setLoanPercentage(Double loanPercentage) {
        this.loanPercentage = loanPercentage;
    }

    public Integer getLoanInstallments() {
        return loanInstallments;
    }

    public void setLoanInstallments(Integer loanInstallments) {
        this.loanInstallments = loanInstallments;
    }
}
