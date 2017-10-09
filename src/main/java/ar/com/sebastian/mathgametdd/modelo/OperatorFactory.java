/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import ar.com.sebastian.mathgametdd.entidad.MathOperator;
import ar.com.sebastian.mathgametdd.exception.InvalidOperationException;

/**
 *
 * @author sebas
 */
public class OperatorFactory {

    private static final String DIVIDE_OPERATOR = "/";
    private static final String MULTIPLY_OPERATOR = "*";
    private static final String SUBSTRACT_OPERATOR = "-";
    private static final String ADD_OPERATOR = "+";
    private static final String PERCENTAGE_OPERATOR = "%";
    
    public static MathOperator create(String token) throws InvalidOperationException {    
        String aux = Operation.ADD.getOperationToken();
        
        switch(token){
            case ADD_OPERATOR: return new AddOperator();
            case SUBSTRACT_OPERATOR: return new SubstractOperator();
            case MULTIPLY_OPERATOR: return new MultiplyOperator();
            case DIVIDE_OPERATOR: return new DivideOperator();
            case PERCENTAGE_OPERATOR: return new PercentageOperator();
            default: throw new InvalidOperationException("The given token is not a valid operator");
        }
    }
}