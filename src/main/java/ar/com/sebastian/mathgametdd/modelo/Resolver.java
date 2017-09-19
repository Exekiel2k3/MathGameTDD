/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import ar.com.sebastian.mathgametdd.entidad.MathToken;
import ar.com.sebastian.mathgametdd.entidad.TokenPrecedence;
import ar.com.sebastian.mathgametdd.exception.InvalidOperationException;
import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class Resolver {
    
    private MathLexer lexer;
    private CalculatorProxy calculatorProxy;
    private TokenPrecedence precedence;

    public Resolver(MathLexer lexer, CalculatorProxy calculatorProxy, TokenPrecedence precedence) {
        this.lexer = lexer;
        this.calculatorProxy = calculatorProxy;
        this.precedence = precedence;
    }
    
    public double resolveSimpleExpression(String expression) throws InvalidOperationException, Exception {    
        
        if(!MathRegex.isExpressionValid(expression))
            throw new InvalidOperationException(expression);
        
        ArrayList<MathToken> mathExp = lexer.getTokens(expression);
        
        while (mathExp.size() > 1) {
            MathToken op = precedence.getMaxPrecedence(mathExp);
            op.setPreviousToken(mathExp.get(op.getIndex()-1));
            op.setNextToken(mathExp.get(op.getIndex()+1));
            
            double result = op.resolve();
            replaceTokensWithResult(mathExp, op.getIndex(), result);
        }
        return mathExp.get(0).resolve();
    }

    private void replaceTokensWithResult(ArrayList<MathToken> tokens, int indexOfOperator, double result) {    
        tokens.set(indexOfOperator-1, new MathNumber(Double.toString(result)));
        tokens.remove(indexOfOperator);
        tokens.remove(indexOfOperator);
    }
}