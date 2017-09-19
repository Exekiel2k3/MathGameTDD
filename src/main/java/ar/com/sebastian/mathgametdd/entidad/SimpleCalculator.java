/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.entidad;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author sebas
 */
public abstract class SimpleCalculator {

    public double add(double arg1, double arg2){    
        return arg1+arg2;
    }
    
    public int add(int arg1, int arg2){
        return arg1+arg2;
    }
    
    public BigDecimal add(BigDecimal arg1, BigDecimal arg2){
        return arg1.add(arg2);
    }
    
    public int substract(int arg1, int arg2){
        return arg1-arg2;
    }

    public double substract(double arg1, double arg2){
        return arg1-arg2;
    }

    public BigDecimal substract(BigDecimal arg1, BigDecimal arg2){
        return arg1.subtract(arg2);
    }
    
    public int multiply(int arg1, int arg2){
        return arg1*arg2;
    }

    public double multiply(double arg1, double arg2){
        return arg1*arg2;
    }

    public BigDecimal multiply(BigDecimal arg1, BigDecimal arg2){
        return arg1.multiply(arg2);
    }
    
    public int division(int arg1, int arg2){
        return arg1/arg2;
    }
    
    public double division(double arg1, double arg2){
        return arg1/arg2;
    }
    
    public BigDecimal division(BigDecimal arg1, BigDecimal arg2) throws ArithmeticException{
        return arg1.divide(arg2, MathContext.DECIMAL128);
    }
}