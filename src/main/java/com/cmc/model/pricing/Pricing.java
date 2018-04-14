/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmc.model.pricing;

/**
 * @Author : KAsun Udayanaga
 * @Document : Pricing
 * @Created on : Jan 21, 2018, 12:35:34 PM
 */
public class Pricing {

    private Double amount;
    private Double amountwithlights;

    /**
     * @return the amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * @return the amountwithlights
     */
    public Double getAmountwithlights() {
        return amountwithlights;
    }

    /**
     * @param amountwithlights the amountwithlights to set
     */
    public void setAmountwithlights(Double amountwithlights) {
        this.amountwithlights = amountwithlights;
    }

}
