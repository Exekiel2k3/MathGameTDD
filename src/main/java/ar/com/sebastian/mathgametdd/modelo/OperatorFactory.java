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

    public static MathOperator create(String token) throws InvalidOperationException {    
        
        switch(token){
            case "+": return new AddOperator();
            case "-": return new SubstractOperator();
            case "*": return new MultiplyOperator();
            case "/": return new DivideOperator();
            default: throw new InvalidOperationException("The given token is not a valid operator");
        }
    }
}
