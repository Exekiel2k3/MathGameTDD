/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.test;

import ar.com.sebastian.mathgametdd.entidad.MathOperator;
import ar.com.sebastian.mathgametdd.exception.InvalidOperationException;
import ar.com.sebastian.mathgametdd.modelo.ExpressionFixer;
import ar.com.sebastian.mathgametdd.modelo.MathRegex;
import ar.com.sebastian.mathgametdd.modelo.MathLexer;
import ar.com.sebastian.mathgametdd.modelo.MathNumber;
import ar.com.sebastian.mathgametdd.entidad.MathToken;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sebas
 */
public class LexerTests {
    
    public MathLexer lexer;
    
    @Before
    public void initialize(){
        lexer = new MathLexer(new ExpressionFixer());
    }

    @Test
    public void getTokens() throws InvalidOperationException{
        
        ArrayList<MathToken> token = null;
        token = lexer.getTokens("2 + 2");
        
        assertEquals(3, token.size());
        assertEquals("2", token.get(0).getToken());
        assertEquals("+", token.get(1).getToken());
        assertEquals("2", token.get(2).getToken());
    }
    
    @Test
    public void validateSimpleExpressionWithAllOperators(){
        
        String operators = "+-*/";
        String expression = "";
        char[] operatorsChar = operators.toCharArray();
        
        for(int i = 0; i < operatorsChar.length; i++ ){
            expression = "2 " + operatorsChar[i] + " 2"; 
            assertTrue(MathRegex.isExpressionValid(expression));
        }
    }
    
    @Test
    public void validateMoreThanOneDigitExpression(){
        String expression = "24 + 2123";
        boolean result = MathRegex.isExpressionValid(expression);
        assertTrue(result);
    }
    
    @Test
    public void validateWithSpaces(){
        String expression = "24    *     2123";
        boolean result = MathRegex.isExpressionValid(expression);
        assertTrue(result);
    }
    
    @Test
    public void validateFailsNoSpaces(){
        String expression = "24/2123";
        boolean result = MathRegex.isExpressionValid(expression);
        assertFalse(result);
    }
    
    @Test
    public void validateComplexExpression(){
        String expression = "2 + 7 - 2 * 4";
        boolean result = MathRegex.isExpressionValid(expression);
        assertTrue(result);
    }
    
    @Test
    public void validateComplexWrongExpression(){
        String expression = "2 + 7 a 2 b 4";
        boolean result = MathRegex.isExpressionValid(expression);
        assertFalse(result);
    }
    
    @Test
    public void validateSimpleWrongExpression(){
        String expression = "2a7";
        boolean result = MathRegex.isExpressionValid(expression);
        assertFalse(result);
    }
    
    @Test
    public void validateWrongExpressionWithValidSubexpression(){
        String expression = "2 + 7 - 2 a 3 b";
        boolean result = MathRegex.isExpressionValid(expression);
        assertFalse(result);
    }
    
    @Test
    public void validateWithSeveralOperatorsTogether(){
        String expression = "+ + 7";
        boolean result = MathRegex.isExpressionValid(expression);
        assertFalse(result);
    }
    
    @Test
    public void validateWithNegativeNumers(){
        String expression = "-7 + 1";
        boolean result = MathRegex.isExpressionValid(expression);
        assertTrue(result);
    }
    
    @Test
    public void validateWithNegativeNumersAtTheEnd(){
        String expression = "7 - -1";
        boolean result = MathRegex.isExpressionValid(expression);
        assertTrue(result);
    }
    
    @Test
    public void validateSuperComplexExpression(){
        String expression = "7 - -1 * 2 / 3 + -5";
        boolean result = MathRegex.isExpressionValid(expression);
        assertTrue(result);
    }
    
    @Test
    public void getTokensLongExpression() throws InvalidOperationException{
        ArrayList<MathToken> tokens = null;
        tokens = lexer.getTokens("2 - 1 + 3");
        assertEquals(5, tokens.size());
        assertEquals("+", tokens.get(3).getToken());
        assertEquals("3", tokens.get(4).getToken());
    }
    
    @Test
    public void getTokensWithSpaces() throws InvalidOperationException{
        
        ArrayList<MathToken> tokens = lexer.getTokens("5 -   88");
        assertEquals("5", tokens.get(0).getToken());
        assertEquals("-", tokens.get(1).getToken());
        assertEquals("88", tokens.get(2).getToken());
    }
    
    @Test
    public void getTokensRightSubclasses() throws InvalidOperationException{
        ArrayList<MathToken> tokens = lexer.getTokens("2 + 2");
        assertTrue(tokens.get(0) instanceof MathNumber);
        assertTrue(tokens.get(1) instanceof MathOperator);
    }
}