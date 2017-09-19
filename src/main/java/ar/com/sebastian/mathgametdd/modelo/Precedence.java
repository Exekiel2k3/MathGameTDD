/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import ar.com.sebastian.mathgametdd.entidad.MathToken;
import ar.com.sebastian.mathgametdd.entidad.MathOperator;
import ar.com.sebastian.mathgametdd.entidad.TokenPrecedence;
import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class Precedence implements TokenPrecedence{

    @Override
    public MathToken getMaxPrecedence(ArrayList<MathToken> tokens) {
        int precedence = 0;
        MathToken maxPrecedenceToken = null;
        int index = -1;
        
        for (MathToken token : tokens) {
            index++;
            if(token.getPrecedence() >= precedence){
                precedence = token.getPrecedence();
                maxPrecedenceToken = token;
                maxPrecedenceToken.setIndex(index);
            }
        }
        return (MathOperator)maxPrecedenceToken;
    }
}