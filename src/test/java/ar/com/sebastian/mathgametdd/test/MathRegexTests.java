/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.test;

import ar.com.sebastian.mathgametdd.modelo.MathRegex;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebas
 */
public class MathRegexTests {
    
    @Test
    public void isSubExpression(){
        assertTrue(!MathRegex.isSubExpression("2 + 2"));
    }
    
    @Test
    public void isNumber(){
        assertTrue(MathRegex.isNumber("22"));
    }
    
    @Test
    public void isOperator(){
        String operators = "*+-/";
        for (Character character : operators.toCharArray()) {
            assertTrue(MathRegex.isOperator(character.toString()));
        }
        
    }
    
}
