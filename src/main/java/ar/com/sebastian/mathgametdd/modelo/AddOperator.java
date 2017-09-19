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
public class AddOperator extends MathOperator{
    private static final String OPERATION = "add";
    
    public AddOperator() {
        super(1);
        super.token = "+";
    }

    @Override
    public double resolve(double arg1, double arg2) throws Exception {        
        return calculatorProxy.binaryOperation(Calculator.class, OPERATION, arg1, arg2);
    }
}
