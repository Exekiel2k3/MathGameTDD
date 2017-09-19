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

//    public int processExpression(String expression) throws Exception {
    public double processExpression(String expression) throws Exception {
        
        ArrayList<String> subExpressions = lexer.getExpressions(expression);
        String flatExpression = "";
        
        for (String subExp : subExpressions) {
            
            if(MathRegex.isExpression(flatExpression.trim()))
//                flatExpression = Integer.toString(resolver.resolveSimpleExpression(flatExpression.trim()));
                flatExpression = Double.toString(resolver.resolveSimpleExpression(flatExpression.trim()));
            
            if(!MathRegex.isSubExpression(subExp))
                flatExpression += resolver.resolveSimpleExpression(subExp);
            else
                flatExpression += " " + subExp + " ";
        }
        
        if(!MathRegex.isSubExpression(flatExpression))
            return resolver.resolveSimpleExpression(flatExpression);
            
//        return Integer.parseInt(flatExpression);
        return Double.valueOf(expression);
    }
}
