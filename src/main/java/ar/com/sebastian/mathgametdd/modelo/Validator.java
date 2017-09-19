/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import ar.com.sebastian.mathgametdd.exception.OverflowException;

/**
 *
 * @author sebas
 */
public class Validator {

    private double maxResultValue;
    private double minResultValue;

    public Validator() {
        this.maxResultValue = Double.MAX_VALUE;
        this.minResultValue = -Double.MAX_VALUE;
    }
    
    public Validator(int minResultValue, int maxResultValue) {
        this.maxResultValue = maxResultValue;
        this.minResultValue = minResultValue;
    }
    
    public double getMaxResultValue() {
        return maxResultValue;
    }

    public void setMaxResultValue(int maxResultValue) {
        this.maxResultValue = maxResultValue;
    }

    public double getMinResultValue() {
        return minResultValue;
    }

    public void setMinResultValue(int minResultValue) {
        this.minResultValue = minResultValue;
    }

    public void validateArgs(double arg1, double arg2) throws OverflowException  {    
        
        breakIfOverflow(arg1, "Primer argumento excede el limite.");
        breakIfOverflow(arg2, "Segundo argumento excede el limite.");
    }
    
    private void breakIfOverflow(double arg, String msg) throws OverflowException{    
        if(valueExceedLimits(arg))
            throw new OverflowException(msg);
    }

    public boolean valueExceedLimits(double arg){    
        if(arg > this.maxResultValue)
            return true;
        
        if(arg < this.minResultValue)
            return true;
        
        return false;
    }

    public void validateResult(double result) throws OverflowException {    
        breakIfOverflow(result, "El resultado excede el limite.");
    }
}
