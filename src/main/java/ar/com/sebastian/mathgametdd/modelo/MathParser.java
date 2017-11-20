/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class MathParser{
    
    private Resolver resolver;
    private MathLexer lexer;

    private static final String WHITE_SPACE = " ";
    private static final String STRING_EMPTY = "";
    
    public MathParser(MathLexer lexer, Resolver resolver) {
        this.lexer = lexer;
        this.resolver = resolver;
    }
    
    public MathLexer getLexer() {
        return lexer;
    }

    public void setLexer(MathLexer lexer) {
        this.lexer = lexer;
    }

    public Resolver getResolver() {
        return resolver;
    }

    public void setResolver(Resolver resolver) {
        this.resolver = resolver;
    }

    public double processExpression(String expression) throws Exception {
        
        ArrayList<String> subExpressions = lexer.getExpressions(expression);
        String flatExpression = STRING_EMPTY;
              
        for (String subExp : subExpressions) {
            
            if(MathRegex.isExpression(flatExpression.trim())){
                double aux = resolver.resolveSimpleExpression(flatExpression.trim());
                flatExpression = Double.toString(aux);
            }
            
            if(!MathRegex.isSubExpression(subExp))
                flatExpression += resolver.resolveSimpleExpression(subExp);
            else
                flatExpression += WHITE_SPACE + subExp + WHITE_SPACE;
        }
        
        if(!MathRegex.isSubExpression(flatExpression))
            return resolver.resolveSimpleExpression(flatExpression);
            
        return Double.valueOf(expression);
    }
}