/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import ar.com.sebastian.mathgametdd.entidad.MathToken;

/**
 *
 * @author sebas
 */
public class MathNumber extends MathToken{
    
    public MathNumber(String token) {
        super(token);
    }

    public MathNumber() {
        super(0);
    }
    
//    public int getIntValue(){
//        return Integer.parseInt(token);
//    }
    
    public double getDoubleValue(){
        return Double.parseDouble(token);
    }
    
    public static int getTokenValue(String token){
        return Integer.parseInt(token);
    }

    @Override
//    public int resolve() {
    public double resolve() {
//        return this.getIntValue();
        return this.getDoubleValue();
    }
}
