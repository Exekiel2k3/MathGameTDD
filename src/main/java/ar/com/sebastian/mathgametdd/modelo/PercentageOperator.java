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
public class PercentageOperator extends MathOperator {
    private static final String OPERATION = Operation.PERCENTAGE.getOperationName();
    
    public PercentageOperator() {
        super(2);
        super.token = Operation.PERCENTAGE.getOperationToken();
    }

    @Override
    public double resolve(double firstNumber, double secondNumber) throws Exception {
        return calculatorProxy.binaryOperation(Calculator.class, OPERATION, firstNumber, secondNumber);
    }
    
}
