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
public class MultiplyOperator extends MathOperator{

    private static final String OPERATION = Operation.MULTIPLY.getOperationName();
    
    public MultiplyOperator() {
        super(2);
        super.token = Operation.MULTIPLY.getOperationToken();
    }

    @Override
    public double resolve(double arg1, double arg2) throws Exception {
        return calculatorProxy.binaryOperation(Calculator.class, OPERATION, arg1, arg2);
    }
}
