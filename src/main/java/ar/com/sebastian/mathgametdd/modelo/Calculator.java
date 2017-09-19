/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import ar.com.sebastian.mathgametdd.entidad.SimpleCalculator;
import java.math.BigDecimal;


/**
 *
 * @author sebas
 */
public class Calculator extends SimpleCalculator{

    private BigDecimal bd1;
    private BigDecimal bd2;
        
    public Calculator() {
    }

    @Override
    public double add(double arg1, double arg2){    
        assingBigDecimals(arg1, arg2);
        return super.add(bd1, bd2).doubleValue();
    }

    @Override
    public double substract(double arg1, double arg2){
        assingBigDecimals(arg1, arg2);
        return super.substract(bd1, bd2).doubleValue();
    }
    
    @Override
    public double multiply(double arg1, double arg2){
        assingBigDecimals(arg1, arg2);
        return super.multiply(bd1, bd2).doubleValue();
    }
    
    @Override
    public double division(double arg1, double arg2) throws ArithmeticException{
        assingBigDecimals(arg1, arg2);
        BigDecimal aux = super.division(bd1, bd2); 
        double aux1 = aux.doubleValue();
        return aux1;
    }
    
    private void assingBigDecimals(double arg1, double arg2) {
        bd1 = new BigDecimal(Double.toString(arg1));
        bd2 = new BigDecimal(Double.toString(arg2));
    }
}