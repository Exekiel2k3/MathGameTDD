/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.entidad;

import ar.com.sebastian.mathgametdd.modelo.Calculator;
import ar.com.sebastian.mathgametdd.modelo.CalculatorProxy;
import ar.com.sebastian.mathgametdd.modelo.Validator;

/**
 *
 * @author sebas
 */
public abstract class MathOperator extends MathToken{
    
    protected CalculatorProxy calculatorProxy;
    
    public MathOperator(int precedence) {
        super(precedence);
        calculatorProxy = new CalculatorProxy(new Validator(), new Calculator());
    }    

    public CalculatorProxy getCalculatorProxy() {
        return calculatorProxy;
    }

    public void setCalculatorProxy(CalculatorProxy calculatorProxy) {
        this.calculatorProxy = calculatorProxy;
    }

    @Override
    public double resolve() throws Exception{    
        return resolve(previousToken.resolve(), nextToken.resolve());
    }

    public abstract double resolve(double firstNumber, double secondNumber) throws Exception;
}
