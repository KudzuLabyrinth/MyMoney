package com.example.a123.mymoney.db;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by 123 on 2017/11/19.
 */

public class CashFlow extends DataSupport {

    private List<Integer> outputCashFlow;

    private List<Integer> inputCashFlow;

    public List<Integer> getOutputCashFlow() {
        return outputCashFlow;
    }

    public void setOutputCashFlow(List<Integer> outputCashFlow) {
        this.outputCashFlow = outputCashFlow;
    }

    public List<Integer> getInputCashFlow() {
        return inputCashFlow;
    }

    public void setInputCashFlow(List<Integer> inputCashFlow) {
        this.inputCashFlow = inputCashFlow;
    }
}
