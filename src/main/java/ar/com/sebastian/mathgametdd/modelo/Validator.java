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

    private static final String SEGUNDO_ARGUMENTO_EXCEDE_EL_LIMITE = "Segundo argumento excede el limite.";
    private static final String PRIMER_ARGUMENTO_EXCEDE_EL_LIMITE = "Primer argumento excede el limite.";
    private static final String EL_RESULTADO_EXCEDE_EL_LIMITE = "El resultado excede el limite.";
    
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
        
        breakIfOverflow(arg1, PRIMER_ARGUMENTO_EXCEDE_EL_LIMITE);
        breakIfOverflow(arg2, SEGUNDO_ARGUMENTO_EXCEDE_EL_LIMITE);
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
        breakIfOverflow(result, EL_RESULTADO_EXCEDE_EL_LIMITE);
    }    
}