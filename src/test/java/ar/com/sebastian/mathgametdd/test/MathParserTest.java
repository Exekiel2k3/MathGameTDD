/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.test;

import ar.com.sebastian.mathgametdd.exception.InvalidOperationException;
import ar.com.sebastian.mathgametdd.exception.OverflowException;
import ar.com.sebastian.mathgametdd.modelo.Calculator;
import ar.com.sebastian.mathgametdd.modelo.CalculatorProxy;
import ar.com.sebastian.mathgametdd.modelo.ExpressionFixer;
import ar.com.sebastian.mathgametdd.modelo.MathLexer;
import ar.com.sebastian.mathgametdd.modelo.MathParser;
import ar.com.sebastian.mathgametdd.entidad.MathToken;
import ar.com.sebastian.mathgametdd.modelo.Precedence;
import ar.com.sebastian.mathgametdd.modelo.Resolver;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.easymock.EasyMockRunner;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author sebas
 */
@RunWith(EasyMockRunner.class)
public class MathParserTest {
    
    private Calculator calculator = new Calculator();
    private CalculatorProxy calculatorProxy = new CalculatorProxy(calculator);
    private ExpressionFixer fixer = new ExpressionFixer();
    private MathLexer lexer = new MathLexer(fixer);
    private Precedence precedence = new Precedence();
    private Resolver resolver = new Resolver(lexer, calculatorProxy, precedence);
    private MathParser parser = new MathParser(lexer,resolver);
    
    @Test
    public void processSimpleExpression() throws Exception{
        try {
            assertEquals(4, parser.processExpression("2 + 2"), 0);
        } catch (OverflowException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(MathParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void processSimpleExpressionWhitNegativeNumber() throws Exception{
        try {
            assertEquals(0, parser.processExpression("2 + -2"), 0);
        } catch (OverflowException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(MathParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void processSimpleExpressionWhitNegativeNumberWhitDouble() throws Exception{
        try {
            assertEquals(0, parser.processExpression("2.91 + -2.91"), 0);
        } catch (OverflowException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(MathParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    @Test(expected = InvalidOperationException.class)
    public void getTokensWrongExpression() throws InvalidOperationException, Exception{
        resolver.resolveSimpleExpression("2 - 1++ 3");
    }
    
    @Test(expected = InvalidOperationException.class)
    public void getTokensWrongExpressionWhitDouble() throws InvalidOperationException, Exception{
        resolver.resolveSimpleExpression("2.1 - 1.17++ 3.09");
    }
    
    @Test
    public void processExpression2Operators() throws Exception {
        assertEquals(6, parser.processExpression("3 + 1 + 2"), 0);
    }
    
    @Test
    public void processExpression2OperatorsWhitDouble() throws Exception {
        assertEquals(2.088, parser.processExpression("0.1 + 0.001 + 1.987"), 0);
    }
    
    @Test
    public void processExpressionWithPrecedence() throws Exception {
        assertEquals(9, parser.processExpression("3 + 3 * 2"), 0);
    }
    
    @Test
    public void processExpressionWithPrecedenceWhitDouble() throws Exception {
        assertEquals(88.76180829, parser.processExpression("3.0001 + 3.8981 * 22.0009"), 0);
    }
    
    @Test
    public void getMaxPrecedence() throws InvalidOperationException{
        ArrayList<MathToken> tokens = lexer.getTokens("3 + 3 * 2");
        MathToken mathToken = precedence.getMaxPrecedence(tokens);
        assertEquals(mathToken.getToken(), "*");
    }
    
    @Test
    public void processAcceptanceExpression() throws Exception{
        assertEquals(9, parser.processExpression("5 + 4 * 2 / 2"), 0);
    }
    
    @Test
    public void processAcceptanceExpressionWhitDouble() throws Exception{
        assertEquals(9.2, parser.processExpression("5.2 + 4.0 * 2.1 / 2.1"), 0);
    }
    
    @Test
    public void processAcceptanceExpressionWithNegativeResult() throws Exception{
        assertEquals(-1, parser.processExpression("4 - 5 * 2 / 2"), 0);
    }
    
    @Test
    public void processAcceptanceExpressionWithNegativeResultWhitDouble() throws Exception{
        assertEquals(-0.9590909090909095, parser.processExpression("4.1 - 5.3 * 2.1 / 2.2"), 0);
    }
    
    @Test
    public void processAcceptanceExpressionWithAllOperators() throws Exception{
        assertEquals(-3.5, parser.processExpression("5 + 4 - 1 * 2 / 2 % 8"), 0);
    }
    
    @Test
    public void processAcceptanceExpressionWithAllOperatorsWhitDouble() throws Exception{
        assertEquals(9.9899, parser.processExpression("5.9999 + 4.99 - 1.00000 * 2.99 / 2.99"), 0);
    }
    
    @Test
    public void getExpressionWithOutParenthesis() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("2 + 2");
        assertEquals(1, expressions.size());
        assertEquals("2 + 2", expressions.get(0));
    }
    
    @Test
    public void getExpressionWithOneParenthesis() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(2 + 2)");
        assertEquals(1, expressions.size());
        assertEquals("2 + 2", expressions.get(0));
    }
    
    @Test
    public void getExpressionWithOneParenthesisWhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(2.99 + 2.010101)");
        assertEquals(1, expressions.size());
        assertEquals("2.99 + 2.010101", expressions.get(0));
    }
    
    @Test
    public void getExpressionsWithNestedParenthesis() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("((2) + 2)");
        assertEquals(3, expressions.size());
        assertEquals("2", expressions.get(0));
        assertEquals("+", expressions.get(1));
        assertEquals("2", expressions.get(2));
    }
    
    @Test
    public void getExpressionsWithNestedParenthesisWhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("((2.00001) + 2.090987)");
        assertEquals(3, expressions.size());
        assertEquals("2.00001", expressions.get(0));
        assertEquals("+", expressions.get(1));
        assertEquals("2.090987", expressions.get(2));
    }
    
    @Test
    public void getNestedExpressionWhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("((2.100001 + 1) + 3.99)");
        assertEquals(3, expressions.size());
        for (String exp : expressions) {
            if((!"2.100001 + 1".equals(exp)) && (!"+".equals(exp)) && (!"3.99".equals(exp))){
                assertFalse(true);
            }
        }
    }
    
    @Test
    public void getNestedExpression() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("((2 + 1) + 3)");
        assertEquals(3, expressions.size());
        for (String exp : expressions) {
            if((!"2 + 1".equals(exp)) && (!"+".equals(exp)) && (!"3".equals(exp))){
                assertFalse(true);
            }
        }
    }
    
    @Test
    public void getExpressionWithParenthesisAtTheEnd() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("2 + (3 * 1)");
        assertEquals(3, expressions.size());
        for (String exp : expressions) {
            if((!"3 * 1".equals(exp)) && (!"+".equals(exp)) && (!"2".equals(exp))){
                assertFalse(true);
            }
        }
    }
    
    @Test
    public void getExpressionWithParenthesisAtTheEndWhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("2.01 + (3.1 * 1.1)");
        assertEquals(3, expressions.size());
        for (String exp : expressions) {
            if((!"3.1 * 1.1".equals(exp)) && (!"+".equals(exp)) && (!"2.01".equals(exp))){
                assertFalse(true);
            }
        }
    }
    
    @Test
    public void getExpressionWithParenthesisAtTheStart() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(3 * 1) + 2");
        assertEquals(3, expressions.size());
        for (String exp : expressions) {
            if((!"3 * 1".equals(exp)) && (!"+".equals(exp)) && (!"2".equals(exp))){
                assertFalse(true);
            }
        }
    }
    
    @Test
    public void getExpressionWithParenthesisAtTheStartWhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(3.1 * 1.1) + 2.2");
        assertEquals(3, expressions.size());
        for (String exp : expressions) {
            if((!"3.1 * 1.1".equals(exp)) && (!"+".equals(exp)) && (!"2.2".equals(exp))){
                assertFalse(true);
            }
        }
    }
    
    @Test
    public void getExpressionWithParenthesisAtTheMiddle() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("1 + (3 * 1) + 2");
        assertEquals(5, expressions.size());
        for (String exp : expressions) {
            if((!"3 * 1".equals(exp)) && (!"+".equals(exp)) && (!"2".equals(exp)) && (!"1".equals(exp))){
                assertFalse(true);
            }
        }
    }
    
    @Test
    public void getExpressionWithParenthesisAtTheMiddleWhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("1.1 + (3.3 * 1.1) + 2.2");
        assertEquals(5, expressions.size());
        for (String exp : expressions) {
            if((!"3.3 * 1.1".equals(exp)) && (!"+".equals(exp)) && (!"2.2".equals(exp)) && (!"1.1".equals(exp))){
                assertFalse(true);
            }
        }
    }
    
    @Test(expected = InvalidOperationException.class)
    public void throwExceptionOnOpenParenthesis() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(2 + 3 * 1");
    }
    
    @Test(expected = InvalidOperationException.class)
    public void throwExceptionOnOpenParenthesisWhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(2.2 + 3 * 1.1");
    }
    
    @Test(expected = InvalidOperationException.class)
    public void throwExceptionOnCloseParenthesis() throws InvalidOperationException{
        lexer = new MathLexer(new ExpressionFixer());
        ArrayList<String> expressions = lexer.getExpressions("2 + 3 * 1)");
    }
    
    @Test(expected = InvalidOperationException.class)
    public void throwExceptionOnCloseParenthesisWhitDouble() throws InvalidOperationException{
        lexer = new MathLexer(new ExpressionFixer());
        ArrayList<String> expressions = lexer.getExpressions("0.2 + 0.13 * 1)");
    }
    
    @Test
    public void getExpressionWithTwoGroups() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(2 + 2) * (3 + 1)");
        assertEquals(3, expressions.size());
        assertEquals("2 + 2", expressions.get(0));
        assertEquals("*", expressions.get(1));
        assertEquals("3 + 1", expressions.get(2));
    }
    
    @Test
    public void getExpressionWithTwoGroupsWhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(2.2 + 2.2) * (3.3 + 1.1)");
        assertEquals(3, expressions.size());
        assertEquals("2.2 + 2.2", expressions.get(0));
        assertEquals("*", expressions.get(1));
        assertEquals("3.3 + 1.1", expressions.get(2));
    }
    
    @Test
    public void getExpressionWithTwoGroups2() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("((2 * 2) + (2 * 1)) * (3 + 1)");
        assertEquals(5, expressions.size());
        assertEquals("2 * 2", expressions.get(0));
        assertEquals("+", expressions.get(1));
        assertEquals("2 * 1", expressions.get(2));
        assertEquals("*", expressions.get(3));
        assertEquals("3 + 1", expressions.get(4));
    }
    
    @Test
    public void getExpressionWithTwoGroups2WhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("((2.2 * 2.2) + (2.2 * 1.1)) * (3.3 + 1.1)");
        assertEquals(5, expressions.size());
        assertEquals("2.2 * 2.2", expressions.get(0));
        assertEquals("+", expressions.get(1));
        assertEquals("2.2 * 1.1", expressions.get(2));
        assertEquals("*", expressions.get(3));
        assertEquals("3.3 + 1.1", expressions.get(4));
    }
    
    @Test
    public void getExpressionWithTwoGroups3() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(((2 * 2) + (2 * 1)) * (3 + 1))");
        assertEquals(5, expressions.size());
        assertEquals("2 * 2", expressions.get(0));
        assertEquals("+", expressions.get(1));
        assertEquals("2 * 1", expressions.get(2));
        assertEquals("*", expressions.get(3));
        assertEquals("3 + 1", expressions.get(4));
    }
    
    @Test
    public void getExpressionWithTwoGroups3WhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(((2.2 * 2.2) + (2.2 * 1.1)) * (3.3 + 1.1))");
        assertEquals(5, expressions.size());
        assertEquals("2.2 * 2.2", expressions.get(0));
        assertEquals("+", expressions.get(1));
        assertEquals("2.2 * 1.1", expressions.get(2));
        assertEquals("*", expressions.get(3));
        assertEquals("3.3 + 1.1", expressions.get(4));
    }
    
    @Test
    public void processAcceptanceExpressionWithParenthesis() throws Exception{
        assertEquals(16, parser.processExpression("(2 + 2) * (3 + 1)"), 0);
    }
    
    @Test
    public void processAcceptanceExpressionWithParenthesisWhitDouble() throws Exception{
        assertEquals(0.16, parser.processExpression("(.2 + .2) * (.3 + .1)"), 0);
    }
    
    @Test
    public void getComplexNestedExpressions() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("((2 + 2) + 1)  * (3 + 1)");
        assertEquals(5, expressions.size());
        assertEquals("2 + 2", expressions.get(0));
        assertEquals("+", expressions.get(1));
        assertEquals("1", expressions.get(2));
        assertEquals("*", expressions.get(3));
        assertEquals("3 + 1", expressions.get(4));
    }
    
    @Test
    public void getComplexNestedExpressionsWhitDouble() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("((.2 + .2) + .1)  * (.3 + .1)");
        assertEquals(5, expressions.size());
        assertEquals(".2 + .2", expressions.get(0));
        assertEquals("+", expressions.get(1));
        assertEquals(".1", expressions.get(2));
        assertEquals("*", expressions.get(3));
        assertEquals(".3 + .1", expressions.get(4));
    }
    
    @Test
    public void processComplexNestedExpressions() throws Exception {
        assertEquals(20, parser.processExpression("((2 + 2) + 1)  * (3 + 1)"), 0);
    }
    
    @Test
    public void processComplexNestedExpressionsWhitDouble() throws Exception {
        assertEquals(0.20, parser.processExpression("((.2 + .2) + .1)  * (.3 + .1)"), 0);
    }

    @Test
    public void processSimplePercentageExpression() throws Exception{
        assertEquals(0.04, parser.processExpression("2 % 2"), 0);
    }

    @Test(expected = InvalidOperationException.class)
    public void getTokensWrongExpressionPercentage() throws InvalidOperationException, Exception{
        resolver.resolveSimpleExpression("2 %% 2");
    }
    
    @Test
    public void getNestedExpressionWhitPercentage() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("((2 % 2) % 3)");
        assertEquals(3, expressions.size());
        for (String exp : expressions) {
            if((!"2 % 2".equals(exp)) && (!"%".equals(exp)) && (!"3".equals(exp))){
                assertFalse(true);
            }
        }
    }
    
    @Test
    public void getExpressionWithTwoGroupsPercentage() throws InvalidOperationException{
        ArrayList<String> expressions = lexer.getExpressions("(2 % 2) * (2 % 2)");
        assertEquals(3, expressions.size());
        assertEquals("2 % 2", expressions.get(0));
        assertEquals("*", expressions.get(1));
        assertEquals("2 % 2", expressions.get(2));
    }
    
    @Test
    public void processAcceptanceExpressionWithParenthesisPercentage() throws Exception{
        assertEquals(0.0016, parser.processExpression("(2 % 2) * (2 % 2)"), 0);
    }
    
    @Test
    public void processComplexNestedExpressionsWhitPercentage() throws Exception {
        assertEquals(0.0016, parser.processExpression("((2 % 2) % 1) * (3 + 1)"), 0);
    }
    
    @Test
    public void processSimpleExpressionsWhitScientificNotation() throws Exception {
        assertEquals(31.0000000031, parser.processExpression("(3.1e-9 + 3.1E+1)"), 0);
    }
    
    @Test
    public void processComplexNestedExpressionsWhitScientificNotation() throws Exception {
        assertEquals(-2.116e-5, parser.processExpression("((2 % 2) % 1) * (2.3e-9 % -2.3E+9)"), 0);
    }
}
