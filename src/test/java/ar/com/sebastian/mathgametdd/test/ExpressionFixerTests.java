/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.test;

import ar.com.sebastian.mathgametdd.modelo.ExpressionFixer;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sebas
 */
public class ExpressionFixerTests {
    
    @Test
    public void splitExpressionWhenOperatorAtTheEnd(){
        ExpressionFixer fixer = new ExpressionFixer();
        ArrayList<String> expressions = new ArrayList<>();
        expressions.add("2 +");
        fixer.fixExpressions(expressions);
        assertTrue(expressions.contains("2"));
        assertTrue(expressions.contains("+"));
    }
    
    @Test
    public void trim(){
        ExpressionFixer fixer = new ExpressionFixer();
        ArrayList<String> expressions = new ArrayList<>();
        expressions.add(" * ");
        fixer.fixExpressions(expressions);
        assertEquals("*",expressions.get(0));
    }
}
