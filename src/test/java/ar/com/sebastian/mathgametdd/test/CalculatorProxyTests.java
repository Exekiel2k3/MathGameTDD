/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.test;

import ar.com.sebastian.mathgametdd.modelo.Validator;
import ar.com.sebastian.mathgametdd.exception.OverflowException;
import ar.com.sebastian.mathgametdd.modelo.Calculator;
import ar.com.sebastian.mathgametdd.modelo.CalculatorProxy;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author sebas
 */
public class CalculatorProxyTests {
    
    private Calculator calculator;
    private CalculatorProxy calculatorProxy;
    private CalculatorProxy calcProxyWithLimits;
    
    @Before
    public void initialize(){
        calculator = new Calculator();
        calculatorProxy = new CalculatorProxy(calculator);
        calcProxyWithLimits = new CalculatorProxy(new Validator(-10,10), calculator);
//        calcProxyWithLimits = new CalculatorProxy(new Validator(), calculator);
    }
    
    @Test
    public void add(){
//        int result = 0;
        double result = 0;
        try {
            result = calculatorProxy.binaryOperation(Calculator.class,"add",2,2);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException | OverflowException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
//        assertEquals(4, result);
        assertEquals(4, result, 0);
    }
    
    @Test
    public void addWhithDouble(){
//        int result = 0;
        double result = 0;
        try {
            result = calculatorProxy.binaryOperation(Calculator.class,"add",2.2,2.2);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException | OverflowException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
//        assertEquals(4.4, result);
        assertEquals(4.4, result, 0);
    }
    
    @Test
    public void substract(){
//        int result = 0;
        double result = 0;
        try {
            result = calculatorProxy.binaryOperation(Calculator.class, "substract", 5, 3);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException | OverflowException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
//        assertEquals(2, result);
        assertEquals(2, result, 0);
    }
    
    @Test
    public void substractWithDouble(){
        double result = 0;
        try {
            result = calculatorProxy.binaryOperation(Calculator.class, "substract", 5.1, 3.9);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException | OverflowException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(1.2, result, 0);
    }
    
    @Test
    public void addWithDifferentArguments(){
//        int result = 0;
        double result = 0;
        try {
            result = calculatorProxy.binaryOperation(Calculator.class,"add",2,5);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException | OverflowException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
//        assertEquals(7, result);
        assertEquals(7, result, 0);
    }
    
    @Test
    public void substractReturningNegative(){
//        int result = 0;
        double result = 0;
        try {
            result = calculatorProxy.binaryOperation(Calculator.class, "substract", 3, 5);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException | OverflowException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
//        assertEquals(-2, result);
        assertEquals(-2, result, 0);
    }
    
    @Test
    public void substractWhitDoubleReturningNegative(){
        double result = 0;
        try {
            result = calculatorProxy.binaryOperation(Calculator.class, "substract", 3.3, 5.11);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException | OverflowException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(-1.81, result, 0);
    }
    
    @Test(expected=OverflowException.class)
    public void argumentsExceedLimits()throws OverflowException{
        
        try {
//            int result = calcProxyWithLimits.binaryOperation(Calculator.class,"add",30,50);
            double result = calcProxyWithLimits.binaryOperation(Calculator.class,"add",30,50);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected=OverflowException.class)
    public void argumentsExceedLimitsInverse()throws OverflowException{
        
        try {
//            int result = calcProxyWithLimits.binaryOperation(Calculator.class,"add",-30,-50);
            double result = calcProxyWithLimits.binaryOperation(Calculator.class,"add",-30,-50);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected = OverflowException.class)
    public void validateResultExceedingUpperLimit()throws OverflowException{
        
        try {
//            int result = calcProxyWithLimits.binaryOperation(Calculator.class, "add", 10, 10);
            double result = calcProxyWithLimits.binaryOperation(Calculator.class, "add", 10, 10);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test(expected = OverflowException.class)
    public void validateResultExceedingLowerLimit() throws OverflowException{
        
        try {
//            int result = calcProxyWithLimits.binaryOperation(Calculator.class, "add", -20, -1);
            double result = calcProxyWithLimits.binaryOperation(Calculator.class, "add", -20, -1);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException | NoSuchMethodException ex) {
            Logger.getLogger(CalculatorProxyTests.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Test
    public void multiply() throws Exception{
//        assertEquals(calculatorProxy.binaryOperation(Calculator.class, "multiply", 2, 5), 10);
        assertEquals(calculatorProxy.binaryOperation(Calculator.class, "multiply", 2, 5), 10, 0);
    }
    
    @Test
    public void division() throws Exception{
//        assertEquals(calculatorProxy.binaryOperation(Calculator.class, "division", 10, 2), 5);
        assertEquals(calculatorProxy.binaryOperation(Calculator.class, "division", 10, 2), 5, 0);
    }

}
