/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.test;

import ar.com.sebastian.mathgametdd.exception.OverflowException;
import ar.com.sebastian.mathgametdd.modelo.Calculator;
import java.math.BigDecimal;
import org.easymock.EasyMockRunner;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;



/**
 *
 * @author sebas
 */
@RunWith(EasyMockRunner.class)
public class CalculatorTests {
    
    private Calculator c0;
    private Calculator calculator;
    
    public CalculatorTests() {
    }
    
    @Before
    public void initialize(){
        calculator = new Calculator();
        c0 = new Calculator();        
    }
    
    @Test
    public void addTest1() throws OverflowException{
        double result = calculator.add(2, 2);
        assertTrue(result==4);
    }
    
    @Test
    public void addTest2() throws OverflowException{
        double result = calculator.add(2, 5);
        assertTrue(result==7);
    }
    
    @Test
    public void addTestWihtDouble() throws OverflowException{
        double n1 = 2.1;
        double n2 = 5.1;
        double result = calculator.add(n1, n2);
        assertTrue(result==7.2);
    }
    
    @Test
    public void substract1() throws OverflowException{
        double result = calculator.substract(5,3);
        assertTrue(result==2);
    }

    @Test
    public void substract2() throws OverflowException{
        double result = calculator.substract(3,5);
        assertTrue(result==-2);
    }
    
    @Test
    public void substractWhitDouble() throws OverflowException{
        double result = calculator.substract(5.25,3.75);
        assertTrue(result==1.5);
    }

    @Test
    public void substractWhitDoubleNegativeResult() throws OverflowException{
        double result = calculator.substract(3.25,5.25);
        assertTrue(result==-2);
    }
    
    @Test
    public void multiplyWhitDouble() throws OverflowException{
        double result = calculator.multiply(5.25,3.75);
        assertTrue(result==19.6875);
    }

    @Test
    public void multiplyWhitDoubleNegativeResult() throws OverflowException{
        double result = calculator.multiply(3.11,-5.22);
        assertTrue(result==-16.2342);
    }
    
    @Test
    public void divisionWhitDouble() throws OverflowException{
        double result = calculator.division(5.25,3.75);
        assertTrue(result==1.4);
    }

    @Test
    public void divisionWhitDoubleNegativeResult() throws OverflowException, ArithmeticException{
        double result = calculator.division(3.11,-5.22);
        assertTrue(result==-0.5957854406130269);
    }
}
