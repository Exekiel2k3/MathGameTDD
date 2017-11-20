/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.test;

import ar.com.sebastian.mathgametdd.exception.InvalidOperationException;
import ar.com.sebastian.mathgametdd.entidad.MathOperator;
import ar.com.sebastian.mathgametdd.modelo.OperatorFactory;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebas
 */
public class OperatorFactoryTest {
    
    public OperatorFactoryTest() {
    }
    
    @Test
    public void createMultiplyOperator() throws InvalidOperationException{
        MathOperator op = OperatorFactory.create("*");
        assertEquals(op.getPrecedence(), 2);
    }
    
    @Test
    public void createDivisionOperator() throws InvalidOperationException{
        MathOperator op = OperatorFactory.create("/");
        assertEquals(op.getPrecedence(), 2);
    }
    
    @Test
    public void createPercentageOperator() throws InvalidOperationException{
        MathOperator op = OperatorFactory.create("%");
        assertEquals(op.getPrecedence(), 2);
    }
}
