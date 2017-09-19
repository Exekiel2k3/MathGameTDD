/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import ar.com.sebastian.mathgametdd.entidad.MathOperator;

/**
 *
 * @author sebas
 */
public class DivideOperator extends MathOperator{

    private static final String OPERATION = "division";
    
    public DivideOperator() {
        super(2);
        super.token = "/";
    }

    @Override
//    public int resolve(int arg1, int arg2) throws Exception {    
    public double resolve(double arg1, double arg2) throws Exception {    
        return calculatorProxy.binaryOperation(Calculator.class, OPERATION, arg1, arg2);
    }
}